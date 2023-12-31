package com.inyaw.blog.vo;

import com.inyaw.blog.bean.InyawBlogArticle;
import com.inyaw.blog.bean.InyawBlogComment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InyawBlogWebInfoVo extends InyawBlogWebVo {

    /**
     * 文章
     */
    private InyawBlogArticle article;

    /**
     * 评论
     */
    private List<InyawBlogComment> commentList;

    private InyawBlogVo previousBlog;

    private InyawBlogVo nextBlog;

}
