package com.alinlis.test02.service.impl;

import com.alinlis.test02.entity.Collect;
import com.alinlis.test02.entity.TArticles;
import com.alinlis.test02.mapper.CollectMapper;
import com.alinlis.test02.service.CollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anonymous
 * @since 2022-05-19
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
@Autowired
CollectMapper collectMapper;
    @Override
    public void add(Collect collect) {
        collectMapper.add(collect);
    }

    @Override
    public List<TArticles> selectbyid(Long uid) {
        return collectMapper.selectByid(uid);
    }
}
