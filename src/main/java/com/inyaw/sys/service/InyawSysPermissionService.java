package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysPermission;
import com.inyaw.sys.dao.InyawSysPermissionDao;
import com.inyaw.sys.dto.InyawSysPermissionDto;
import com.inyaw.sys.vo.InyawSysPermissionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InyawSysPermissionService {

    private final InyawSysPermissionDao inyawSysPermissionDao;

    public List<InyawSysPermission> findAll() {
        return inyawSysPermissionDao.findAll();
    }

    public List<InyawSysPermission> findMenuList(InyawSysPermission permission) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<InyawSysPermission> ex = Example.of(permission, matcher);
        Sort sort = Sort.by("sort").ascending();
        return inyawSysPermissionDao.findAll(ex, sort);
    }

    public void delete(InyawSysPermission permission) {
        inyawSysPermissionDao.deleteById(permission.getId());
        String key = "CONFIG_ATTRIBUTE";
       // redisService.del(key);
    }

    public void save(InyawSysPermission permission) {
        if (permission.getParentId() == null) {
            permission.setParentId(0);
        }
        inyawSysPermissionDao.save(permission);
        String key = "CONFIG_ATTRIBUTE";
        //redisService.del(key);
    }

    private List<InyawSysPermission> findMenuList(Integer pid, Boolean enable) {
        InyawSysPermission params = new InyawSysPermission();
        if (enable != null && enable) {
            params.setIsShow(true);
        }
        params.setParentId(pid);
        return findMenuList(params);
    }

    private List<InyawSysPermissionDto> findMenuList(int roleId, List<InyawSysPermission> list, Integer isWx) {
        List<InyawSysPermissionDto> menuVOS = new ArrayList<>();
        list.forEach(menuInfo -> {
            InyawSysPermissionDto dto = new InyawSysPermissionDto();
            BeanUtils.copyProperties(menuInfo, dto);

            List<InyawSysPermission> chindres = inyawSysPermissionDao.findMenuList(menuInfo.getId(), roleId);
            if (chindres.size() > 0) {
                dto.setChildren(findMenuList(roleId, chindres, isWx));
            }
            menuVOS.add(dto);
        });
        return menuVOS;
    }

    public List<InyawSysPermissionDto> findMenuList(int roleId, Integer isWx) {
        List<InyawSysPermission> list = inyawSysPermissionDao.findMenuList(0, roleId);
        return findMenuList(roleId, list, isWx);
    }

    public List<String> findMenuPathList(int roleId) {
        return inyawSysPermissionDao.findMenuPathList(roleId);
    }

    public List<String> findButtonList(Integer roleId) {
        return inyawSysPermissionDao.findButtonList(true, roleId);
    }

    private List<InyawSysPermissionVo> findPermissionList(List<InyawSysPermission> list, Boolean enable) {
        List<InyawSysPermissionVo> menuVOS = new ArrayList<>();
        list.forEach(menuInfo -> {
            InyawSysPermissionVo vo = new InyawSysPermissionVo();
            BeanUtils.copyProperties(menuInfo, vo);

            List<InyawSysPermission> chindres = findMenuList(menuInfo.getId(), enable);
            if (chindres.size() > 0) {
                vo.setChildren(findPermissionList(chindres, enable));
            }
            menuVOS.add(vo);
        });
        return menuVOS;
    }

    public List<InyawSysPermissionVo> findPermissionList(Boolean enable) {
        return findPermissionList(findMenuList(0, enable), enable);
    }
}
