package com.inyaw.sys.dto;

import com.inyaw.sys.bean.InyawSysUser;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class LoginUser extends InyawSysUser implements Serializable {

    private static final long serialVersionUID = -1312531418281641819L;
    private String token;

}
