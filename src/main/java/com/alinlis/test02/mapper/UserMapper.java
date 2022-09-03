package com.alinlis.test02.mapper;

import com.alinlis.test02.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anonymous
 * @since 2022-02-22
 */
public interface UserMapper extends BaseMapper<User> {

    void Disable(Integer id);
    void addUser(User user);
    Integer getbyuser(String username);

    void uploadimg(User user);

    void change(User user);
}
