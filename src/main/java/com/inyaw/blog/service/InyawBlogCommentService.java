package com.inyaw.blog.service;

import com.inyaw.blog.bean.InyawBlogComment;
import com.inyaw.blog.dao.InyawBlogCommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InyawBlogCommentService {

    private final InyawBlogCommentDao inyawBlogCommentDao;

    public void saveBlogComment(InyawBlogComment req) {
        req.setCreateTime(LocalDateTime.now());
        inyawBlogCommentDao.save(req);
    }
}
