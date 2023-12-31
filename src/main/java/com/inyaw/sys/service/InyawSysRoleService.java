package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysMenu;
import com.inyaw.sys.bean.InyawSysRole;
import com.inyaw.sys.dao.InyawSysRoleDao;
import com.inyaw.sys.dto.InyawSysRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InyawSysRoleService {

    private final InyawSysRoleDao inyawSysRoleDao;
    private final InyawSysMenuService inyawSysMenuService;

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

    public void saveByMenuIdList(InyawSysRoleDto roleDto) {
        InyawSysRole roleInfo = new InyawSysRole();
        BeanUtils.copyProperties(roleDto, roleInfo);
        List<InyawSysMenu> menuList = new ArrayList<>();
        if (roleDto.getMenuIdList() != null && roleDto.getMenuIdList().size() > 0) {
            menuList = inyawSysMenuService.findAllById(roleDto.getMenuIdList());
            roleInfo.setMenuList(menuList);
        }
        inyawSysRoleDao.save(roleInfo);
    }
}
