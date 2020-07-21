package com.alisls.demo.elasticsearch.high.web;

import com.alisls.demo.elasticsearch.high.dto.UserInfoDTO;
import com.alisls.demo.elasticsearch.high.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 用户信息管理
 * 管理Elasticsearch中的用户、角色、用户角色
 *
 * @author Ke Wang
 * @date 2020/7/17
 */
@RestController
@RequestMapping("/userinfo")
@AllArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping("/save")
    public UserInfoDTO save(@RequestBody UserInfoDTO userInfoDTO) {
        try {
            return userInfoService.save(userInfoDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
