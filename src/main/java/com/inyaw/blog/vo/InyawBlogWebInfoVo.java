package com.inyaw.blog.vo;

import com.inyaw.blog.bean.BlogArticle;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InyawBlogWebInfoVo extends InyawBlogWebVo {

    /**
     * 文章
     */
    private BlogArticle article;

    private InyawBlogVo previousBlog;

    private InyawBlogVo nextBlog;

}
