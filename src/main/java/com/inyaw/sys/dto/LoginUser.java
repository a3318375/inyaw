package com.inyaw.sys.dto;

import com.inyaw.sys.bean.SysUser;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUser extends SysUser {

    private String token;

}
