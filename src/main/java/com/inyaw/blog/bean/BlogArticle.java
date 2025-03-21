package com.inyaw.blog.bean;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;


@Table("blog_article")
@Data
public class BlogArticle {

    @Id(keyType = KeyType.Auto)
    private Integer id;
    /**
     * 文章内容
     */
    private String context;
}
