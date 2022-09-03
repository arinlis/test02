package com.alinlis.test02.mapper;

import com.alinlis.test02.entity.Collect;
import com.alinlis.test02.entity.TArticles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anonymous
 * @since 2022-05-19
 */
public interface CollectMapper extends BaseMapper<Collect> {

    void add(Collect collect);


    List<TArticles> selectByid(Long uid);
}
