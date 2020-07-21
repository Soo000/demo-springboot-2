package com.alisls.demo.elasticsearch.high.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 业务服务类
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
public class BusiServiceImpl {

    public ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换成Json字符串
     * @param userInfoDTO
     * @return
     * @throws JsonProcessingException
     */
    public String objectToJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

}
