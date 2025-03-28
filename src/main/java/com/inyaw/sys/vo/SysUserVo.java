package com.inyaw.sys.vo;

import com.inyaw.sys.bean.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SysUserVo extends SysUser {

    private List<SysMenuVo> menuList;

}
