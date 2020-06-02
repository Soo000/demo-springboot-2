package com.alisls.demo.springboot.jpa.elasticsearch.service;

import com.alisls.demo.springboot.jpa.elasticsearch.dto.UserDTO;

/**
 * UserService
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
public interface UserService {

    UserDTO getByUsername(String username);

    UserDTO saveUser(UserDTO userDTO);

}
