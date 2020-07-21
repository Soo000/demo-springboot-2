package com.alisls.demo.elasticsearch.high.service.impl;

import com.alisls.demo.elasticsearch.high.dto.UserInfoDTO;
import com.alisls.demo.elasticsearch.high.service.DocumentService;
import com.alisls.demo.elasticsearch.high.service.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 用户信息服务
 *
 * @author Ke Wang
 * @date 2020/7/17
 */
@Service("userInfoService")
@AllArgsConstructor
@Slf4j
public class UserInfoServiceImpl extends BusiServiceImpl implements UserInfoService {

    private final static String USER_INFO_INDEX_NAME = "user_info";

    private final DocumentService documentService;

    @Override
    public UserInfoDTO save(UserInfoDTO userInfoDTO) throws IOException {
        String userInfoJson = objectToJson(userInfoDTO);
        documentService.saveDocument(USER_INFO_INDEX_NAME, null, userInfoJson);
        return userInfoDTO;
    }

}
