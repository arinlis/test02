package com.alinlis.test02.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alinlis.test02.entity.Comments;
import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.service.CommentsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.alinlis.test02.controller.TArticlesController.getDateTimeFormat;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {
@Autowired
    public CommentsService commentsService;


@PostMapping("/selectallbyid")
@ApiOperation(value = "根据文章id查评论")
public List<Comments> SelectbyId(@RequestBody String str){
    JSONObject jsonObject= JSON.parseObject(str);
    String articleId=jsonObject.getString("id");
    Integer id=Integer.valueOf(articleId);
    return commentsService.selectbyId(id);
}

@PostMapping("/addcomment")
@ApiOperation(value = "发布评论")
public RespBean addcomment(@RequestParam String articleid,String content,String userid){
//@RequestBody是对json格式的参数转换为Java类型，
//用于接收Content-Type为application/json类型的请求,数据类型是JSON：{“aaa”:“111”,“bbb”:“222”}
//
//不使用@RequestBody注解时，可以接收Content-Type为application/x-www-form-urlencoded类型的请求所提交的数据，数据格式：aaa=111&bbb=222 ,form表单提交以及jQuery的.post()方法所发送的请求就是这种类型。
//————————————————
//版权声明：本文为CSDN博主「家家小迷弟」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/weixin_42260782/article/details/110139264
    if (content==null){
        return RespBean.error("评论内容不能为空，请重新输入");
    }else {
    String time= getDateTimeFormat(new Date());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
    Long aid=Long.valueOf(articleid);
    Long uid=Long.valueOf(userid);
    Comments comments=new Comments();
    comments.setArticleId(aid);
    comments.setCommentContent(content);
    comments.setUserId(uid);
    comments.setCommentDate(dateTime);
    commentsService.insert(comments);

   System.out.println(articleid);
    System.out.println(content);
    System.out.println(userid);

    return RespBean.success("评论成功");
    }
}
@PostMapping("/commentimg")
@ApiOperation(value = "评论图片")
public String upload(@RequestBody MultipartFile file){
    String oldName = file.getOriginalFilename();
    //获取文件存储的后缀名
    String suffix = oldName.substring(oldName.lastIndexOf(".") + 1);
    //创建文件新的名字+后缀
    String filename = System.currentTimeMillis() + "." + suffix;
    String filepath = "D:\\img\\";
    File dest = new File(filepath + filename);
    String fileurl="http://localhost:8080/img/"+filename;
    System.out.println(filename);
    try {
        file.transferTo(dest);
    } catch (Exception e) {
        e.printStackTrace();
    }
        return fileurl;
    }
    @PostMapping("/selectallbypage")
    @ApiOperation(value = "分页查所有")
    public RespPageBean Selectbypage(@RequestParam Integer currentPage, @RequestParam Integer size){
    return commentsService.pagefindall(currentPage,size);
    }
    @PostMapping("/delete")
    @Transactional()
    @ApiOperation(value = "删除")
    public RespBean delete(@RequestBody String uid){
    uid=uid.replace("=", "");
    Long id =Long.valueOf(uid);
    commentsService.delete(id);
    return RespBean.success("删除成功");
    }

}
