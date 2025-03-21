package com.inyaw.sys.service;

import com.inyaw.sys.bean.SysMenu;
import com.inyaw.sys.bean.SysRole;
import com.inyaw.sys.bean.SysRoleMenu;
import com.inyaw.sys.dao.SysMenuMapper;
import com.inyaw.sys.dao.SysRoleMapper;
import com.inyaw.sys.dao.SysRoleMenuMapper;
import com.inyaw.sys.dto.SysRoleDto;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SysRoleService {

    private final SysRoleMapper sysRoleMapper;
    private final SysMenuMapper sysMenuMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    public void save(SysRole roleInfo) {
        sysRoleMapper.insert(roleInfo);
    }

    public void delete(SysRole roleInfo) {
        sysRoleMapper.deleteById(roleInfo.getId());
    }

    public List<SysRole> list() {
        return sysRoleMapper.selectAll();
    }

    public SysRole getById(Integer roleId) {
        return sysRoleMapper.selectOneById(roleId);
    }

    public List<SysRole> findAll(SysRole role) {
        QueryWrapper queryWrapper = QueryWrapper.create(role);
        return sysRoleMapper.selectListByQuery(queryWrapper);
    }

    public void saveByMenuIdList(SysRoleDto roleDto) {
        sysRoleMapper.insert(roleDto);
        if (roleDto.getMenuIdList() != null && !roleDto.getMenuIdList().isEmpty()) {
            List<SysMenu> menuList = sysMenuMapper.selectListByIds(roleDto.getMenuIdList());
            List<SysRoleMenu> roleMenuList = new ArrayList<>();
            menuList.forEach(menu -> {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRoleId(roleDto.getId());
                roleMenu.setMenuId(menu.getId());
                roleMenuList.add(roleMenu);
            });
            sysRoleMenuMapper.insertBatch(roleMenuList);
        }
    }
}
