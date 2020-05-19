package com.alisls.demo.springboot.swagger.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<E> {

    private Integer retCode;
    private String retMsg;
    private E e;

    public Result(Integer retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public Result(Integer retCode, String retMsg, E e) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.e = e;
    }

    public static Result success(Integer retCode, String retMsg) {
        return new Result<>(retCode, retMsg);
    }

}
