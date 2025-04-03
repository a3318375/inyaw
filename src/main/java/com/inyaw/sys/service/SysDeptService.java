package com.inyaw.sys.service;

import com.inyaw.sys.bean.SysDept;
import com.inyaw.sys.dao.SysDeptMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SysDeptService extends ServiceImpl<SysDeptMapper, SysDept> {

}
