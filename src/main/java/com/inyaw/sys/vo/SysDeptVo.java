package com.inyaw.sys.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.inyaw.sys.bean.SysDept;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-04-08
 * @Description:
 */
@Setter
@Getter
public class SysDeptVo extends SysDept {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SysDeptVo> children;

}
