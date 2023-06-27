package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysMenu;
import com.inyaw.sys.dao.InyawSysMenuDao;
import com.inyaw.sys.vo.InyawSysMenuVo;
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
public class InyawSysMenuService {

    private final InyawSysMenuDao inyawSysMenuDao;

    public List<InyawSysMenu> findAll() {
        return inyawSysMenuDao.findAll();
    }

    public List<InyawSysMenu> findMenuList(InyawSysMenu permission) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<InyawSysMenu> ex = Example.of(permission, matcher);
        Sort sort = Sort.by("sort").ascending();
        return inyawSysMenuDao.findAll(ex, sort);
    }

    public void delete(InyawSysMenu permission) {
        inyawSysMenuDao.delete(permission);
    }

    public void save(InyawSysMenu permission) {
        if (permission.getParentId() == null) {
            permission.setParentId(0);
        }
        inyawSysMenuDao.save(permission);
    }

    private List<InyawSysMenu> findMenuList(Integer pid, Boolean enable) {
        InyawSysMenu params = new InyawSysMenu();
        if (enable != null ) {
            params.setIsShow(enable);
        }
        params.setParentId(pid);
        return findMenuList(params);
    }

    private List<InyawSysMenuVo> findMenuList(List<InyawSysMenu> list, Boolean enable) {
        List<InyawSysMenuVo> menuVOS = new ArrayList<>();
        list.forEach(menuInfo -> {
            InyawSysMenuVo vo = new InyawSysMenuVo();
            BeanUtils.copyProperties(menuInfo, vo);

            List<InyawSysMenu> chindres = findMenuList(menuInfo.getId(), enable);
            if (chindres.size() > 0) {
                vo.setChildren(findMenuList(chindres, enable));
            }
            menuVOS.add(vo);
        });
        return menuVOS;
    }

    public List<InyawSysMenuVo> findMenuList(Boolean enable) {
        return findMenuList(findMenuList(0, enable), enable);
    }

}
