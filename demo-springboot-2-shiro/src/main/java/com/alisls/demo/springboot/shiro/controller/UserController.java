package com.alisls.demo.springboot.shiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springboot.shiro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getPassword/{username}")
    public String getPassword(@PathVariable String username) {
        return userService.getPasswordByUsername(username);
    }

    @RequestMapping("/list")
    public String list() {
        return "User List";
    }

}
