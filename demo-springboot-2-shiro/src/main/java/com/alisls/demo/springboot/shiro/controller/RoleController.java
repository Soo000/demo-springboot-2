package com.alisls.demo.springboot.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/list")
    public String list() {
        return "ok";
    }

}
