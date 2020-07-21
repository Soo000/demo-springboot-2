package com.alisls.demo.elasticsearch.high.service;

import com.alisls.demo.elasticsearch.high.dto.UserInfoDTO;

import java.io.IOException;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/7/17
 */
public interface UserInfoService {

    UserInfoDTO save(UserInfoDTO userInfoDTO) throws IOException;

}
