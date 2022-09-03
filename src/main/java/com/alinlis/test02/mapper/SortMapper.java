package com.alinlis.test02.mapper;

import com.alinlis.test02.entity.Sort;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anonymous
 * @since 2022-05-19
 */
public interface SortMapper extends BaseMapper<Sort> {

    IPage<Sort> getsortpage(Page<Sort> page);
    
}
