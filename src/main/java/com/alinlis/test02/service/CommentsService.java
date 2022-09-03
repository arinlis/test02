package com.alinlis.test02.service;

import com.alinlis.test02.entity.Comments;
import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.entity.TArticles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anonymous
 * @since 2022-04-12
 */
public interface CommentsService extends IService<Comments> {
 List<Comments> selectbyId(Integer Id) ;
 RespPageBean pagefindall(Integer currents, Integer size);
 void insert(Comments comments);
 void  delete(Long id);

}
