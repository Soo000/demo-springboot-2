package com.alisls.demo.springboot.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/notLogin")
    public String notLogin() {
        return "您需先进要登录，才能进行操作！";
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        // 创建登录 token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            // 登录
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "failed";
        }

        return "ok";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "ok";
    }

}
