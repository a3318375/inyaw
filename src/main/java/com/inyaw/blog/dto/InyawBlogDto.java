package com.inyaw.blog.dto;

import com.inyaw.blog.bean.InyawBlog;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InyawBlogDto extends InyawBlog {

    private String context;

    private InyawBlog previousBlog;

    private InyawBlog nextBlog;

    private Integer page = 1;

    private Integer size = 10;

    public Integer getPage() {
        if (page > 0) {
            return page - 1;
        }
        return page;
    }
}
