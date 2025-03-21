package com.inyaw.blog.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InyawBlogWebInfoVo extends InyawBlogWebVo {

    private String context;

    private InyawBlogVo previousBlog;

    private InyawBlogVo nextBlog;

}
