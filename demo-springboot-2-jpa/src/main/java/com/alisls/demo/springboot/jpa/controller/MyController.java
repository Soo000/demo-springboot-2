package com.alisls.demo.springboot.jpa.controller;

import com.alisls.demo.springboot.jpa.dto.UserDTO;
import com.alisls.demo.springboot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByUserId/{userId}")
    public UserDTO findByUserId(@PathVariable Integer userId) {
        return userService.findByUserId(userId);
    }

}
