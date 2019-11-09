package com.alisls.demo.springboot.web.controller;

import com.alisls.demo.springboot.web.entity.User;
import com.alisls.demo.springboot.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByUserId/{userId}")
    public User findByUserId(@PathVariable Integer userId) {
        return userService.findByUserId(userId);
    }
}
