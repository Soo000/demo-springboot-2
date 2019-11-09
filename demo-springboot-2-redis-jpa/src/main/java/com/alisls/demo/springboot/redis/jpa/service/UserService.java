package com.alisls.demo.springboot.redis.jpa.service;

import com.alisls.demo.springboot.redis.jpa.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Integer userId);

    List<UserDTO> findByAgeLessThan(Integer age);

    List<UserDTO> findAll();

    UserDTO save(UserDTO userDTO);

    void clearAllCache();

    void clear(Integer userId);
}
