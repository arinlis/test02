package com.alinlis.test02.config;

/**
 * @Author: Alinlis
 * @Data: 2022/2/23 17:14
 */

import com.alinlis.test02.entity.User;
import com.alinlis.test02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * @author mornd
 * @date 2022/1/31 - 13:34
 * security配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;
    //注入当未登录或者token失效时访问接口时，自定义的返回结果
    @Autowired
    private  RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    private  RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    /**
     * 配置全局的认证相关的信息
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //需要直接放行的请求
        web.ignoring().antMatchers(
                "/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html",
                "favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/register",
                "/swagger-resources/**",
                "/captcha",
                "/img/**",
                "/v2/api-docs/**");
    }

    /**
     * security主配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //基于jwt，舍弃csrf
        http.csrf()
                .disable()
                //基于token，舍弃session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //所有请求都要认证
                .anyRequest()
                .authenticated()

                //动态权限配置
                //什么样的角色才能访问什么样的url，否则403
                .and()
                //禁用缓存
                .headers()
                .cacheControl();
        //添加jwt登录过滤器
        http.addFilterBefore(jwtAuthorizationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }

    /**
     * 重写UserDetailsService的登录方法
     * @return
     */

    @Override
    @Bean
    public UserDetailsService userDetailsService()  {
        return username -> {
            User user = userService.getUserByUsername(username);
            if(null!=user){
                return user;
            }
            //如果用户存在则给该用户设置角色列表;
            return null;
        };
    }

    /**
     * 注入jwt登录授权过滤器
     * @return
     */
    @Bean
    public JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter(){
        return new JwtAuthorizationTokenFilter();
    }
    /**
     * 注入密码加解密类
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
