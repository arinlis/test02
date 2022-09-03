package com.alinlis.test02.mapper;

import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.Sort;
import com.alinlis.test02.entity.TArticles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anonymous
 * @since 2022-04-10
 */
public interface TArticlesMapper extends BaseMapper<TArticles> {
    void addarticle(TArticles tArticles);
    List<TArticles> selectArticleAll();
    TArticles selectArticle(Integer articleId);
    List<TArticles> selectArticlebysort(String sort);
    List<TArticles> search(String string);
    List<TArticles> selevtByUid(Integer id);
    List<Long> getnew();

    IPage<Sort> getarticlepage(Page<Sort> page);
}
