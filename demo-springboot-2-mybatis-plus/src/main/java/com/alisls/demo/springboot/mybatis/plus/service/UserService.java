package com.alisls.demo.springboot.mybatis.plus.service;

import com.alisls.demo.springboot.mybatis.plus.dto.UserDTO;

import java.util.List;

/**
 * UserService
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
public interface UserService {

    UserDTO getUser(Long id);

    List<UserDTO> listAll();

    UserDTO saveUser(UserDTO userDTO);
}
