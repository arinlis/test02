package com.alinlis.test02.mapper;

import com.alinlis.test02.entity.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author anonymous
 * @since 2022-04-12
 */
public interface CommentsMapper extends BaseMapper<Comments> {
    List<Comments> selectbyId(Integer id);
    void addComment(Comments comments);
    IPage<Comments> getCommentpage( Page<Comments> page);
    void delete(Long id);

}
