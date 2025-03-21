package com.inyaw.blog.vo;

import com.inyaw.blog.bean.BlogInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: yuxh
 * @date: 2021/3/15 0:27
 */
@Setter
@Getter
public class InyawBlogArchiveVo {

    private String archiveDate;

    private Integer articleTotal;

    private List<BlogInfo> archivePosts;

}
