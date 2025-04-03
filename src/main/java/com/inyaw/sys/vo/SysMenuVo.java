package com.inyaw.sys.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inyaw.sys.bean.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SysMenuVo extends SysMenu {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SysMenuVo> children;

    private Map<String, Object> meta;
}
