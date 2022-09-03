package com.alinlis.test02.service;

import com.alinlis.test02.entity.Collect;
import com.alinlis.test02.entity.TArticles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anonymous
 * @since 2022-05-19
 */
public interface CollectService extends IService<Collect> {

    void add(Collect collect);

    List<TArticles> selectbyid(Long uid);
}
