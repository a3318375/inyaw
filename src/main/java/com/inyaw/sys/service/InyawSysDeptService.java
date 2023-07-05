package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysDept;
import com.inyaw.sys.dao.InyawSysDeptDao;
import com.inyaw.sys.dto.InyawSysDeptDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InyawSysDeptService {

    private final InyawSysDeptDao inyawSysDeptDao;

    public void save(InyawSysDept dept) {
        if (dept.getCreateTime() == null) {
            dept.setCreateTime(LocalDateTime.now());
        }
        if (dept.getParentId() == null) {
            dept.setParentId(0);
        }
        inyawSysDeptDao.save(dept);
    }

    public void delete(InyawSysDept dept) {
        inyawSysDeptDao.delete(dept);
    }

    public List<InyawSysDeptDto> findDeptTree(InyawSysDept dept) {
        dept.setParentId(0);
        List<InyawSysDept> deptList = findDeptList(dept);
        return findDeptList(dept, deptList);
    }

    public List<InyawSysDept> findDeptList(InyawSysDept dept) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<InyawSysDept> ex = Example.of(dept, matcher);
        Sort sort = Sort.by("sort").ascending();
        return inyawSysDeptDao.findAll(ex, sort);
    }

    private List<InyawSysDeptDto> findDeptList(InyawSysDept params, List<InyawSysDept> initDataList) {
        List<InyawSysDeptDto> deptResp = new ArrayList<>();
        initDataList.forEach(dept -> {
            InyawSysDeptDto dto = new InyawSysDeptDto();
            BeanUtils.copyProperties(dept, dto);

            params.setParentId(dept.getId());
            List<InyawSysDept> chindres = findDeptList(params);
            if (chindres.size() > 0) {
                dto.setChildren(findDeptList(params, chindres));
            }
            deptResp.add(dto);
        });
        return deptResp;
    }
}
