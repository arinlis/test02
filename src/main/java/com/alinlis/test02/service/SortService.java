package com.alinlis.test02.service;

import com.alinlis.test02.entity.RespPageBean;
import com.alinlis.test02.entity.Sort;
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
public interface SortService extends IService<Sort> {

    RespPageBean getbypage(Integer currentPage, Integer size);
}
