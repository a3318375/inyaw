package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysRole;
import com.inyaw.sys.dao.InyawSysRoleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InyawSysRoleService {

    private final InyawSysRoleDao inyawSysRoleDao;

    public void save(InyawSysRole roleInfo) {
        inyawSysRoleDao.save(roleInfo);
    }

    public void delete(InyawSysRole roleInfo) {
        inyawSysRoleDao.deleteById(roleInfo.getId());
    }

    public List<InyawSysRole> list() {
        return inyawSysRoleDao.findAll();
    }

    public InyawSysRole getById(Integer roleId) {
        return inyawSysRoleDao.getById(roleId);
    }

    public List<InyawSysRole> findAll(InyawSysRole role) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<InyawSysRole> ex = Example.of(role, matcher);
        return inyawSysRoleDao.findAll(ex);
    }
}
