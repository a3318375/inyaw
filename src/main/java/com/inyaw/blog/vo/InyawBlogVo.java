package com.inyaw.blog.vo;

import com.inyaw.blog.bean.BlogInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InyawBlogVo extends BlogInfo {

    private String context;

    private BlogInfo previousBlog;

    private BlogInfo nextBlog;

}
