package com.alisls.demo.springboot.web.service;

import com.alisls.demo.springboot.web.entity.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    public User findByUserId(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        user.setUsername("wangke");
        return user;
    }

}
