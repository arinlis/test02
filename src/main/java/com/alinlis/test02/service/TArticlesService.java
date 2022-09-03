package com.alinlis.test02.service;

import com.alinlis.test02.entity.RespBean;
import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.entity.TArticles;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anonymous
 * @since 2022-04-10
 */
public interface TArticlesService extends IService<TArticles> {
 RespBean addarticle(TArticles articles);
 List<Long> getnew();
 List<TArticles> selectUsersAll();
 List<TArticles> selerctbySort(String sort);
 List<TArticles> search(String string);
 List<TArticles> selectbyUid(Integer id);


 TArticles selectArticle(Integer articleId);

 void upload(TArticles tArticles);

    RespPageBean selectbypage(Integer currentPage, Integer size);
}

