package com.inyaw.base;

/**
 * @author: yuxh
 * @date: 2021/2/27 11:30
 */
public class BaseResult<R> {

    private int code;

    private String message;

    private R data;

    public static <R> BaseResult<R> success() {
        return new BaseResult<R>().setCode(200).setMessage("成功");
    }

    public static <R> BaseResult<R> success(R data) {
        return new BaseResult<R>().setCode(200).setMessage("成功").setData(data);
    }

    public static <R> BaseResult<R> error(int code, String msg) {
        BaseResult<R> result = new BaseResult<>();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static <R> BaseResult<R> error() {
        BaseResult<R> result = new BaseResult<>();
        result.setCode(-1);
        result.setMessage("失败");
        return result;
    }

    public int getCode() {
        return code;
    }

    public BaseResult<R> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BaseResult<R> setMessage(String message) {
        this.message = message;
        return this;
    }

    public R getData() {
        return data;
    }

    public BaseResult<R> setData(R data) {
        this.data = data;
        return this;
    }

}
