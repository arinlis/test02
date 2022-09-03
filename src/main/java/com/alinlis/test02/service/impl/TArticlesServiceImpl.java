package com.alinlis.test02.service.impl;

import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.entity.Sort;
import com.alinlis.test02.entity.TArticles;
import com.alinlis.test02.mapper.TArticlesMapper;
import com.alinlis.test02.service.TArticlesService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anonymous
 * @since 2022-04-10
 */
@Service
public class TArticlesServiceImpl extends ServiceImpl<TArticlesMapper, TArticles> implements TArticlesService {

    @Autowired
    TArticlesMapper tArticlesMapper;


    @Override
    public RespBean addarticle(TArticles tArticles) {
        tArticlesMapper.addarticle(tArticles);
        return RespBean.success("发布成功");
    }

    @Override
    public List<Long> getnew() {
        return tArticlesMapper.getnew();
    }

    @Override
    public List<TArticles> selectUsersAll() {
        return tArticlesMapper.selectArticleAll();
    }

    @Override
    public List<TArticles> selerctbySort(String sort) {
        return tArticlesMapper.selectArticlebysort(sort);
    }

    @Override
    public List<TArticles> search(String string) {
        return tArticlesMapper.search(string);
    }

    @Override
    public List<TArticles> selectbyUid(Integer id) {
        return tArticlesMapper.selevtByUid(id);
    }



    @Override
    public TArticles selectArticle(Integer articleId) {
        return tArticlesMapper.selectArticle(articleId);
    }

    @Override
    public void upload(TArticles tArticles) {
         tArticlesMapper.addarticle(tArticles);
    }

    @Override
    public RespPageBean selectbypage(Integer currentPage, Integer size) {
        Page<com.alinlis.test02.entity.Sort> page=new Page<>(currentPage,size);
        IPage<Sort> commentpage = tArticlesMapper.getarticlepage(page);
        RespPageBean respPageBean=new RespPageBean(commentpage.getTotal(),commentpage.getRecords());
        return respPageBean;
    }


}
