package com.inyaw.blog.dto;

import com.inyaw.blog.bean.InyawBlogComment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InyawBlogCommentDto extends InyawBlogComment {

    private List<InyawBlogComment> children;

}
