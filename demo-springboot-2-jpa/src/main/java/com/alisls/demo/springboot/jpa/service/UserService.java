package com.alisls.demo.springboot.jpa.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alisls.demo.springboot.jpa.dto.UserDTO;
import com.alisls.demo.springboot.jpa.entity.UserDO;
import com.alisls.demo.springboot.jpa.repository.UserRepository;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findByUserId(Integer userId) {
        UserDO userDO = userRepository.findByUserId(userId);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    public UserDTO findByUsername(String username) {
        UserDO userDO = userRepository.findByCond(username);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

}
