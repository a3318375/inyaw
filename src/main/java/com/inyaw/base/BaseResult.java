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
public class BaseResult<R> {

    private int code;

    private String errorMessage;

    private R data;

    public static <R> BaseResult<R> success() {
        return new BaseResult<R>().setCode(1);
    }

    public static <R> BaseResult<R> success(R data) {
        return new BaseResult<R>().setCode(1).setData(data);
    }

    public static <R> BaseResult<R> error(int code, String msg) {
        BaseResult<R> result = new BaseResult<>();
        result.setCode(code);
        result.setErrorMessage(msg);
        return result;
    }

    public static <R> BaseResult<R> error() {
        BaseResult<R> result = new BaseResult<>();
        result.setCode(-1);
        result.setErrorMessage("失败");
        return result;
    }
}
