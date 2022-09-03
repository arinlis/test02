package com.alinlis.test02.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.entity.TArticles;
import com.alinlis.test02.service.TArticlesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2022-04-10
 */
@RestController
public class TArticlesController {
    @Autowired
    TArticlesService tArticlesService;

@PostMapping("/articles")
@ApiOperation(value = "//新建提问")
public RespBean addArticle(@RequestBody TArticles articles){
     return tArticlesService.addarticle(articles);
}
 @GetMapping("/articles/allinfo")
 @ApiOperation(value = "查所有提问")
public List<TArticles> getArticle(){
        return tArticlesService.selectUsersAll();
    }
@PostMapping("/articles/info")
 @ApiOperation(value = "查一个提问")
 public TArticles selectArticle(@RequestBody String str){
        JSONObject jsonObject=JSON.parseObject(str);
       String articleId=jsonObject.getString("id");
       Integer id=Integer.valueOf(articleId);
        return tArticlesService.selectArticle(id);
    }
    /**
     * 日期格式化yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getDateTimeFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }


 @PostMapping("/articles/upload")
 @ApiOperation(value = "upload")
public RespBean upload(@RequestParam("file") MultipartFile file,String article,String sort,Integer id)throws IOException {
     String time= getDateTimeFormat(new Date());
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
     LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
     long userId=id;
     //1.确定文件的存储路径
     //使用ServletContext动态获取upload的路径
     //2.确定文件的存储名字
     //获取文件原始名

     String oldName = file.getOriginalFilename();
     //获取文件存储的后缀名
     String suffix = oldName.substring(oldName.lastIndexOf(".") + 1);
     //创建文件新的名字+后缀
     String filename = System.currentTimeMillis() + "." + suffix;
     String filepath = "D:\\img\\";
     File dest = new File(filepath + filename);
     try {
         file.transferTo(dest);
     } catch (Exception e) {
         e.printStackTrace();
         return RespBean.error(filename + "上传失败");
     }

     System.out.println("name="+oldName);
     System.out.println("格式="+suffix);
     System.out.println("新name="+filename);
     System.out.println("时间="+dateTime);
     System.out.println("id="+id);


     //        if (file.isEmpty()){
//            return RespBean.error("文件不存在");
//        }
//        String originalFilename = file.getOriginalFilename();
//        String filename=System.currentTimeMillis()+"."+originalFilename.substring(originalFilename.lastIndexOf("."+1
//        ));
//        String filepath="D:\\img\\";
//        File dest=new File(filepath+filename);
//        try {
//            file.transferTo(dest);
//        }catch (Exception e){
//            e.printStackTrace();
//            return  RespBean.error(originalFilename+"上传失败");
//        }
//        return RespBean.success("上传成功");

         JSONObject jsonObject1 = JSON.parseObject(article);
         String articleTitle = (String) jsonObject1.get("title");
         String articlecentent= (String) jsonObject1.get("content");
//         System.out.println("article标题=" + articleTitle);
//         System.out.println("内容"+articlecentent);

     TArticles tArticles =new TArticles();
     tArticles.setArticleTitle(articleTitle);
     tArticles.setArticleContent(articlecentent);
     tArticles.setArticleDate(dateTime);
     tArticles.setArticleCover("http://localhost:8080/img/"+filename);
     tArticles.setUserId(userId);
     tArticles.setArticleSort(sort);

     System.out.println(time);

     tArticlesService.upload(tArticles);
      return RespBean.success("发布成功");

    }

@PostMapping("/articles/selectbysort")
@ApiOperation(value = "分类查文章")
public List<TArticles> selectbysort(@RequestBody String sort) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode( sort, "utf-8");
        decode=decode.replace("=", "");
       decode=decode.replace(" ", "+");
        System.out.println(decode);
        return tArticlesService.selerctbySort(decode);
}
    @PostMapping("/articles/search")
    @ApiOperation(value = "搜索")
public List<TArticles> search(@RequestBody String text) throws UnsupportedEncodingException {
        String string = URLDecoder.decode( text, "utf-8");
        string=string.replace("=", "");
        return tArticlesService.search(string);
    }
    @PostMapping("/articles/selectbyuserid")
    @ApiOperation(value = "通过用户id查文章")
    public List<TArticles> selectbyUserId(@RequestBody String user){
        JSONObject jsonObject=JSON.parseObject(user);
        String articleId=jsonObject.getString("id");
        int id = Integer.parseInt(articleId);
        return tArticlesService.selectbyUid(id);
    }
    @PostMapping("/articles/getnew")
    @ApiOperation(value = "获取最近文章id")
    public List<Long> getnew(){
        return tArticlesService.getnew();
    }
    @PostMapping("/articles/selectbypage")
    @ApiOperation(value = "分页查")
    public RespPageBean selectbypage(@RequestParam Integer currentPage, @RequestParam Integer size){
      return  tArticlesService.selectbypage(currentPage,size);
    }

}

