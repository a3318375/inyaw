package com.inyaw.blog.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class InyawBlogArticle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    private Integer id;

    /**
     * 文章内容
     */
    @Lob
    @Column(columnDefinition = "text")
    private String context;
}
