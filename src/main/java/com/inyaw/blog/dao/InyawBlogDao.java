package com.inyaw.blog.dao;

import com.inyaw.blog.bean.InyawBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InyawBlogDao extends JpaRepository<InyawBlog, Integer> {

    InyawBlog findTopByIdLessThanOrderByCreateTimeDesc(int id);

    InyawBlog findTopByIdGreaterThanOrderByCreateTimeAsc(int id);

}
