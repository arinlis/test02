package com.alinlis.test02.controller;

import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.User;
import com.alinlis.test02.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Alinlis
 * @Data: 2022/3/16 14:11
 */
@RestController
public class RegisterController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public RespBean saveUser(@RequestBody User user) {
        Integer id=userService.getid(user.getUsername());
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(id);
        if(id!=null) {
            //新增用户，所以把表单的密码加密一下
            return RespBean.error("用户已经存在");
        }
        String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userService.saveUser(user);
    }
}
