package com.inyaw.base;import com.mybatisflex.annotation.Column;import lombok.Setter;import lombok.experimental.Accessors;@Setter@Accessors(chain = true)public class BaseDto {    @Column(ignore = true)    private Integer pageNum = 1;    @Column(ignore = true)    private Integer pageSize = 10;    public Integer getPageNum() {        if (pageNum > 0) {            return pageNum - 1;        }        return pageNum;    }    public Integer getPageSize() {        return pageSize;    }}