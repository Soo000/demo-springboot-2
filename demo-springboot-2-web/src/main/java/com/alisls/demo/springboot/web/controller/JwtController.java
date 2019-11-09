package com.alisls.demo.springboot.web.controller;

import com.alisls.demo.springboot.web.entity.User;
import com.alisls.demo.springboot.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/getToken")
    public String getToken() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("wangke");
        return tokenService.getToken(user);
    }

    @GetMapping("/verifyToken/{token}")
    public String verfiyToken(@PathVariable String token) {
        tokenService.verifyToken(token);
        return "ok";
    }

    @GetMapping("/decodeToken/{token}")
    public String decodeToken(@PathVariable String token) {
        return tokenService.decodeToken(token);
    }

}
