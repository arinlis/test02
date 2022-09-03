package com.alinlis.test02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author anonymous
 * @since 2022-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_articles")
public class TArticles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 提问ID
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /**
     * 提问用户ID
     */
    private Long userId;

    /**
     * 提问标题
     */
    private String articleTitle;

    /**
     * 提问内容
     */
    private String articleContent;

    /**
     * 浏览量
     */
    private Long articleViews;

    /**
     * 评论总数
     */
    private Long articleCommentCount;

    private String articleSort;

    private String articleCover;
    private Long articleTag;

    /**
     * 发表时间
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime articleDate;

    private Long articleLikeCount;
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private  String userFace;
    @TableField(exist = false)
    private List<Comments> comments;
}
