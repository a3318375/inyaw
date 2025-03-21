package com.inyaw.blog.dao;

import com.inyaw.blog.bean.BlogInfo;
import com.mybatisflex.core.BaseMapper;

public interface BlogInfoMapper extends BaseMapper<BlogInfo> {

    BlogInfo findTopByIdLessThanOrderByCreateTimeDesc(int id);

    BlogInfo findTopByIdGreaterThanOrderByCreateTimeAsc(int id);

}
