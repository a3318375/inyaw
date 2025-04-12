package com.inyaw.blog.service;

import com.inyaw.blog.bean.TypeInfo;
import com.inyaw.blog.dao.TypeInfoMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeInfoService extends ServiceImpl<TypeInfoMapper, TypeInfo> {

}
