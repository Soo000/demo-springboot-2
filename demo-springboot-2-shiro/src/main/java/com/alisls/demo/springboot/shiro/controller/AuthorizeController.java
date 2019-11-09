package com.alisls.demo.springboot.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizeController {

    /**
     *
     * @return
     */
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "您没有权限访问！";
    }

}
