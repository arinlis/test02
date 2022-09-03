package com.alinlis.test02.service.impl;

import com.alinlis.test02.entity.Comments;
import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.entity.Sort;
import com.alinlis.test02.mapper.SortMapper;
import com.alinlis.test02.service.SortService;
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
 * @since 2022-05-19
 */
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort> implements SortService {
@Autowired
SortMapper sortMapper;
    @Override
    public RespPageBean getbypage(Integer currentPage, Integer size) {
        Page<Sort> page=new Page<>(currentPage,size);
        IPage<Sort> commentpage = sortMapper.getsortpage(page);
        RespPageBean respPageBean=new RespPageBean(commentpage.getTotal(),commentpage.getRecords());
        return respPageBean;

    }
}
