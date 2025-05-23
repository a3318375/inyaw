package com.inyaw.blog.service;

import com.inyaw.blog.bean.TagInfo;
import com.inyaw.blog.dao.TagInfoMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagInfoService extends ServiceImpl<TagInfoMapper, TagInfo> {

}
