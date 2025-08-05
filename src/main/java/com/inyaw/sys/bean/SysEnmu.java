package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("sys_enmu")
@Data
public class SysEnmu {

    private Long id;

    private String code;

    private String value;

    private String module;
}
