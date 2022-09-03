package com.alinlis.test02.service;

import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anonymous
 * @since 2022-02-22
 */
public interface UserService extends IService<User> {

    void  Disable(Integer id);
     User getUserByUsername(String username);

     RespBean login(String username, String password, String code, HttpServletRequest request);

     RespBean saveUser(User user);

     Integer getid(String username);

    void upload(User user);

    void change(User user);
}
