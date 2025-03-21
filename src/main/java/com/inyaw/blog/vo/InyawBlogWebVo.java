package com.inyaw.blog.vo;

import com.inyaw.blog.bean.BlogTag;
import com.inyaw.blog.bean.TypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

;

@Setter
@Getter
public class InyawBlogWebVo extends InyawBlogVo {

    /**
     * 博客分类
     */
    private TypeInfo type;

    /**
     * 博客标签
     */
    private List<BlogTag> tagList;
}
