package com.inyaw.blog.dto;

import com.inyaw.blog.bean.BlogInfo;
import com.inyaw.blog.bean.TagInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BlogInfoDto extends BlogInfo {

    private List<TagInfo> tagList;

}
