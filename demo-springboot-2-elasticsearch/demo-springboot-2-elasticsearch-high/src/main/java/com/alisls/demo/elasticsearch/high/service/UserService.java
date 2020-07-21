package com.alisls.demo.elasticsearch.high.service;

import com.alisls.demo.elasticsearch.high.dto.UserDTO;

import java.io.IOException;
import java.util.List;

/**
 * UserService
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
public interface UserService extends DocumentService<UserDTO, Long> {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户列表
     */
    List<UserDTO> getByUsername(String username) throws IOException;

    /**
     * 根据用户编码和用户名查询用户
     * @param userCode 用户编码
     * @param username 用户名
     * @return 用户列表
     */
    List<UserDTO> getByUserCodeAndUsername(String userCode, String username) throws IOException;

    /**
     * 保存用户
     * @param userDTO 用户DTO
     * @return 用户DTO
     * @throws IOException 异常
     */
    UserDTO saveUser(UserDTO userDTO) throws IOException;

}
