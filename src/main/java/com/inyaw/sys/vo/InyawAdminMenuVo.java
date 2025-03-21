package com.inyaw.sys.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InyawAdminMenuVo {

    private String title;
    private Integer value;
    private List<InyawAdminMenuVo> children;

}
