package com.inyaw.blog.dao;

import com.inyaw.blog.bean.InyawBlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InyawBlogArticleDao extends JpaRepository<InyawBlogArticle, Integer> {

}
