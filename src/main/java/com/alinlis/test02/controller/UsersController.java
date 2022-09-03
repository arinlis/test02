package com.alinlis.test02.controller;


import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.User;
import com.alinlis.test02.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.alinlis.test02.controller.TArticlesController.getDateTimeFormat;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;
    @PostMapping("/disable")
    @ApiOperation(value = "禁用用户")
    public RespBean Disable(@RequestBody String uid){
        uid=uid.replace("=", "");
        Integer id=Integer.valueOf(uid);
        userService.Disable(id);
        return  RespBean.success("已禁用");
    }
    @PostMapping("/upload")
    @ApiOperation(value = "头像")
    public RespBean upload(@RequestParam("file") MultipartFile file,String id){
        Integer  uid=Integer.valueOf(id);
        String time= getDateTimeFormat(new Date());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
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
        String filename1="http://localhost:8080/img/"+filename;
        System.out.println(filename);
        System.out.println(uid);
        System.out.println(filename1);
        User user=new User();
        user.setUserFace(filename1);
        user.setId(uid);
        userService.upload(user);
     return  RespBean.success("上传成功");
    }
    @PostMapping("/articles/insert")
    @ApiOperation(value = "修改")
    public RespBean insertuser(@RequestParam  String id,String username,String name,String phone,String telephone,String address){
        Integer uid=Integer.valueOf(id);
        User user=new User();
        user.setName(name);
        user.setUsername(username);
        user.setPhone(phone);
        user.setTelephone(telephone);
        user.setAddress(address);
        user.setId(uid);
        userService.change(user);
        return RespBean.success("修改成功");
    }
}
