package com.alinlis.test02.service.impl;

import com.alinlis.test02.config.JWTTokenUtil;
import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.User;
import com.alinlis.test02.mapper.UserMapper;
import com.alinlis.test02.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anonymous
 * @since 2022-02-22
 */

@Service
@Slf4j
public  class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JWTTokenUtil jwtTokenUtil;
    @Autowired
    UserMapper userMapper;
    @Value("${jwt.tokenHead}")
    public String tokenHead;
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha=(String)request.getSession().getAttribute("captcha");
        System.out.println("输入的"+code);
        if (!captcha.equalsIgnoreCase(code)){
            System.out.println(code);
            return RespBean.error("验证码输入错误，请重新输入");
        }

        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null==userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }

        if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员！");
        }
        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                ,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }

    @Override
    public RespBean saveUser(User user) {
        userMapper.addUser(user);
        return RespBean.success("注册成功");
    }

    @Override
    public Integer getid(String username) {
        return userMapper.getbyuser(username);
    }

    @Override
    public void upload(User user) {
        userMapper.uploadimg(user);
    }

    @Override
    public void change(User user) {
        userMapper.change(user);
    }


    @Override
    public void Disable(Integer id) {
        userMapper.Disable(id);
    }

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username",username));
    }


}
