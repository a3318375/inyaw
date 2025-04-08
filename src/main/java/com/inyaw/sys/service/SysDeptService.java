package com.inyaw.sys.service;

import com.inyaw.sys.bean.SysDept;
import com.inyaw.sys.dao.SysDeptMapper;
import com.inyaw.sys.vo.SysDeptVo;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SysDeptService extends ServiceImpl<SysDeptMapper, SysDept> {

    private final SysDeptMapper sysDeptMapper;

    public List<SysDeptVo> list(SysDept dept) {
        QueryWrapper queryWrapper = QueryWrapper.create(dept);
        queryWrapper.orderBy("sort");
        return getMapper().selectListByQueryAs(queryWrapper, SysDeptVo.class);
    }

    public List<SysDeptVo> tree() {
        SysDept params = new SysDept();
        params.setParentId(0);
        return findDeptTreeList(list(params));
    }

    private List<SysDeptVo> findDeptTreeList(List<SysDeptVo> list) {
        list.forEach(deptVo -> {
            SysDept params = new SysDept();
            params.setParentId(deptVo.getId());
            List<SysDeptVo> childList = list(params);
            if (!childList.isEmpty()) {
                deptVo.setChildren(findDeptTreeList(childList));
            }
        });
        return list;
    }
}
