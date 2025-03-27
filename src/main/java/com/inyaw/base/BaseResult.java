package com.inyaw.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: yuxh
 * @date: 2021/2/27 11:30
 */
@Setter
@Getter
@Accessors(chain = true)
public class BaseResult {

    private Boolean success;

    private int code;

    private String msg;

    private Object data;

    public static BaseResult success() {
        return new BaseResult().setSuccess(true).setCode(200);
    }

    public static BaseResult success(Object data) {
        return new BaseResult().setSuccess(true).setCode(200).setData(data);
    }

    public static BaseResult error(int code, String msg) {
        return new BaseResult().setSuccess(false).setCode(code).setMsg(msg);
    }

    public static BaseResult error() {
        return new BaseResult().setSuccess(false).setCode(500).setMsg("失败");
    }
}
