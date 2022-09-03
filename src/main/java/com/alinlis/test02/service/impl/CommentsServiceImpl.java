package com.alinlis.test02.service.impl;

import com.alinlis.test02.entity.Comments;
import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.mapper.CommentsMapper;
import com.alinlis.test02.service.CommentsService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2022-04-12
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

    @Autowired
    public  CommentsMapper commentsMapper;
    @Override
    public List<Comments> selectbyId(Integer Id) {
        return commentsMapper.selectbyId(Id);
    }

    @Override
    public RespPageBean pagefindall(Integer currents, Integer size) {
        Page<Comments> page=new Page<>(currents,size);
        IPage<Comments> commentpage = commentsMapper.getCommentpage(page);
        RespPageBean respPageBean=new RespPageBean(commentpage.getTotal(),commentpage.getRecords());
        return respPageBean;
    }

    @Override
    public void insert(Comments comments) {
        commentsMapper.addComment(comments);
    }

    @Override
    public void delete(Long id) {
        commentsMapper.delete(id);
    }


}
