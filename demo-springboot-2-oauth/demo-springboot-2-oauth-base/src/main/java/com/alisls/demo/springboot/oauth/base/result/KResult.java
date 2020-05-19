package com.alisls.demo.springboot.oauth.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义响应结构
 */
@Data
public class KResult implements Serializable {

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public KResult() {
    }
    public KResult(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }
    public KResult(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public KResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static KResult ok() {
        return new KResult(null);
    }
    public static KResult ok(String message) {
        return new KResult(message, null);
    }
    public static KResult ok(Object data) {
        return new KResult(data);
    }
    public static KResult ok(String message, Object data) {
        return new KResult(message, data);
    }

    public static KResult build(Integer code, String message) {
        return new KResult(code, message, null);
    }

    public static KResult build(Integer code, String message, Object data) {
        return new KResult(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    /**
     * JSON字符串转成 KResult 对象
     * @param json
     * @return
     */
    public static KResult format(String json) {
        try {
            return JSON.parseObject(json, KResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
