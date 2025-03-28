package com.inyaw.sys.service;

import com.inyaw.sys.bean.SysMenu;
import com.inyaw.sys.dao.SysMenuMapper;
import com.inyaw.sys.vo.SysMenuVo;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SysMenuService {

    private final SysMenuMapper sysMenuMapper;

    public List<SysMenu> findAll() {
        return sysMenuMapper.selectAll();
    }

    public List<SysMenu> findAllById(List<Integer> idList) {
        return sysMenuMapper.selectListByIds(idList);
    }

    public List<SysMenu> findMenuList(SysMenu user) {
        QueryWrapper queryWrapper = QueryWrapper.create(user);
        queryWrapper.orderBy("sort");
        return sysMenuMapper.selectListByQuery(queryWrapper);
    }

    public void delete(SysMenu permission) {
        sysMenuMapper.delete(permission);
    }

    public void save(SysMenu permission) {
        if (permission.getParentId() == null) {
            permission.setParentId(0);
        }
        sysMenuMapper.insert(permission);
    }

    private List<SysMenu> findMenuList(Integer pid) {
        SysMenu params = new SysMenu();
        params.setParentId(pid);
        return findMenuList(params);
    }

    private List<SysMenuVo> findMenuList(List<SysMenu> list) {
        List<SysMenuVo> menuVOS = new ArrayList<>();
        list.forEach(menuInfo -> {
            SysMenuVo vo = new SysMenuVo();
            BeanUtils.copyProperties(menuInfo, vo);
            Map<String, Object> meta = new HashMap<>();
            meta.put("title", menuInfo.getTitle());
            vo.setMeta(meta);

            List<SysMenu> chindres = findMenuList(menuInfo.getId());
            if (!chindres.isEmpty()) {
                vo.setChildren(findMenuList(chindres));
            }
            menuVOS.add(vo);
        });
        return menuVOS;
    }

    public List<SysMenuVo> findMenuList() {
        List<SysMenuVo> menuList = findMenuList(findMenuList(0));
        menuList.forEach(menu -> {
            if (menu.getChildren() == null) {
                List<SysMenuVo> children = new ArrayList<>();
                SysMenuVo child = new SysMenuVo();
                BeanUtils.copyProperties(menu, child);
                child.setPath(child.getPath() + "/index");
                children.add(child);
                menu.setChildren(children);
                menu.setName(null);
            }
        });
        return menuList;
    }

}
