package com.alisls.demo.springboot.jpa.service;

import com.alisls.demo.springboot.jpa.dto.user.UserDTO;

/**
 * 用户Service
 *
 * @author Ke Wang
 */
public interface UserService {

    UserDTO getById(Long id);

    UserDTO getByCode(String usercode);

    UserDTO getByUsername(String username);

    UserDTO save(UserDTO userDTO);

    UserDTO saveUserAndRole(UserDTO userRoleDTO);

    UserDTO update(UserDTO userDTO);

}
