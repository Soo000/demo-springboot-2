package com.alisls.demo.springboot.shiro.dto;

import java.io.Serializable;

public class Result implements Serializable {

    public static final String SUCC_RET_CODE = "0001";
    public static final String SUCC_RET_MSG = "ok";

    private String retCode;
    private String retMsg;

    public Result(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

}
