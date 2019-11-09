package com.alisls.demo.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alisls.demo.springboot.web.entity.User;
import com.alisls.demo.springboot.web.service.TokenService;

@RestController
public class LoginController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password) {
        String token = "";
        if ("wangke".equals(username) && "123456".equals(password)) {
            User user = new User();
            user.setUserId(1);
            user.setUsername(username);
            token = tokenService.getToken(user);
        }

        return token;
    }

}
