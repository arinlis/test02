package com.alinlis.test02.controller;

/**
 * @Author: Alinlis
 * @Data: 2022/2/25 18:01
 */


import com.alinlis.test02.entity.AdminLoginParam;
import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.User;
import com.alinlis.test02.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author mornd
 * @date 2021/1/31 - 13:08
 * 登录控制器
 */
@Api(tags = "Login-Controller")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param adminLoginParam
     * @param request
     * @return
     */
    //@LogStar("登录")
    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return userService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    //@LogStar("获取当前登录的用户信息")
    @ApiOperation(value = "获取当前登录的用户信息")
    @GetMapping("/admin/info")
    public User getAdminInfo(Principal principal){
        if(null == principal){
            return null;
        }
        //获取当前登录的用户名
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        user.setPassword(null);
        return user;
    }

    //@LogStar("注销")
    @ApiOperation(value = "用户退出")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }
}
