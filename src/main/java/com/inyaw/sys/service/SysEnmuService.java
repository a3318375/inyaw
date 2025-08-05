package com.inyaw.sys.service;

import com.inyaw.sys.bean.SysEnmu;
import com.inyaw.sys.dao.SysEnmuMapper;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SysEnmuService {

    private final SysEnmuMapper sysEnmuMapper;

    public void save(SysEnmu sysEnmu) {
        sysEnmuMapper.insert(sysEnmu);
    }

    public List<SysEnmu> findAll(String type) {
        SysEnmu params = new SysEnmu();
        params.setCode(type);
        QueryWrapper queryWrapper = QueryWrapper.create(params);
        return sysEnmuMapper.selectListByQuery(queryWrapper);
    }
}
