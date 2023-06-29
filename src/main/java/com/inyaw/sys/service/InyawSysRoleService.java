package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysRole;
import com.inyaw.sys.bean.InyawSysRolePermission;
import com.inyaw.sys.dao.InyawSysRoleDao;
import com.inyaw.sys.dao.InyawSysRolePermissionDao;
import com.inyaw.sys.vo.InyawSysRoleVo;
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
    private final InyawSysRolePermissionDao inyawSysRolePermissionDao;

    public void save(InyawSysRoleVo roleInfo) {
        InyawSysRole role = new InyawSysRole();
        BeanUtils.copyProperties(roleInfo, role);
        inyawSysRoleDao.save(role);
        inyawSysRolePermissionDao.deleteByRoleId(role.getId());
        if (roleInfo.getMenuList() != null && roleInfo.getMenuList().size() > 0) {
            List<InyawSysRolePermission> rolePermissionList = new ArrayList<>();
            roleInfo.getMenuList().forEach(menuId -> {
                InyawSysRolePermission rolePermission = new InyawSysRolePermission();
                rolePermission.setRoleId(role.getId());
                rolePermission.setPermissionId(menuId);
                rolePermissionList.add(rolePermission);
            });
            inyawSysRolePermissionDao.saveAll(rolePermissionList);
        }
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

    public List<InyawSysRoleVo> findRoleList(InyawSysRole role) {
        List<InyawSysRole> lit = findAll(role);
        List<InyawSysRoleVo> respList = new ArrayList<>();
        lit.forEach(roleBean -> {
            InyawSysRoleVo vo = new InyawSysRoleVo();
            BeanUtils.copyProperties(roleBean, vo);
            List<Integer> permissionList = inyawSysRolePermissionDao.findPermissionIdByRoleId(vo.getId());
            vo.setMenuList(permissionList);
            respList.add(vo);
        });
        return respList;
    }
}
