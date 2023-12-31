package com.inyaw.blog.vo;

import com.inyaw.blog.bean.InyawBlogTag;
import com.inyaw.blog.bean.InyawBlogType;
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
    private InyawBlogType type;

    /**
     * 博客标签
     */
    private List<InyawBlogTag> tagList;
}
