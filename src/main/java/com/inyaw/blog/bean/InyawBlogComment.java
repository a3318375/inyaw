package com.inyaw.blog.bean;

import com.inyaw.sys.bean.InyawSysUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class InyawBlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Integer id;

    /**
     * 用户id
     */
    @OneToOne(fetch = FetchType.LAZY)
    private InyawSysUser fromUser;

    /**
     * 评论的目标用户id
     */
    @OneToOne(fetch = FetchType.LAZY)
    private InyawSysUser toUser;

    /**
     * 0-评论博客，1-评论用户
     */
    private Integer type;

    /**
     * 评论的父节点id
     */
    private Integer pid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复时间
     */
    private LocalDateTime createTime;

}
