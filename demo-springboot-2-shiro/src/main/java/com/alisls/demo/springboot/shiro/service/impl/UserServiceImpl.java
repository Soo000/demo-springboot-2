package com.alisls.demo.springboot.shiro.service.impl;

import com.alisls.demo.springboot.shiro.dao.UserDao;
import com.alisls.demo.springboot.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String getPasswordByUsername(String username) {
        return userDao.getPasswordByUsername(username);
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        return userDao.getRolesByUsername(username);
    }

}
