package com.alisls.demo.springboot.redis.jpa.common;

import lombok.Data;

@Data
public class GeneralResponse<T> {

    private String code;

    private String msg;

    private T data;

    public static <T> GeneralResponse<T> failedResponse(String failedString) {
        GeneralResponse<T> response = new GeneralResponse<T>();
        response.setMsg(failedString);
        return response;
    }

    public static <T> GeneralResponse<T> successfulResponse(String successfulString) {
        GeneralResponse<T> response = new GeneralResponse<T>();
        response.setMsg(successfulString);
        return response;
    }

}
