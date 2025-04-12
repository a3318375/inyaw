package com.inyaw.blog.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InyawBlogWebInfoVo extends InyawBlogInfoVo {

    private InyawBlogVo previousBlog;

    private InyawBlogVo nextBlog;

}
