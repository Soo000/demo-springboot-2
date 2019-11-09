package com.alisls.demo.springboot.redis.jpa.service.impl;

import com.alisls.demo.springboot.redis.jpa.dto.UserDTO;
import com.alisls.demo.springboot.redis.jpa.entity.UserDO;
import com.alisls.demo.springboot.redis.jpa.repository.UserRepository;
import com.alisls.demo.springboot.redis.jpa.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceWithJpa implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceWithJpa.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findById(Integer userId) {
        UserDTO userDTO = new UserDTO();

        UserDO userDO = userRepository.findById(userId).orElse(null);
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDO> users = userRepository.findAll();
        if (users != null && !users.isEmpty()) {
            List<UserDTO> myUsers = new ArrayList<UserDTO>();
            for (UserDO userDO: users) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userDO, userDTO);
            }
            return myUsers;
        }
        return null;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public void clearAllCache() {

    }

    @Override
    public void clear(Integer userId) {

    }

    @Override
    public List<UserDTO> findByAgeLessThan(Integer age) {
        return null;
    }
}
