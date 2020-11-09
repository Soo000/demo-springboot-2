package com.alisls.demo.springboot.jpa.controller;

import com.alisls.demo.springboot.jpa.dto.user.UserDTO;
import com.alisls.demo.springboot.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author Ke Wang
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/getByCode/{code}")
    public UserDTO getByCode(@PathVariable String code) {
        return userService.getByCode(code);
    }

    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PostMapping("/saveUserAndRole")
    public UserDTO saveUserAndRole(@RequestBody UserDTO userDTO) {
        return userService.saveUserAndRole(userDTO);
    }

    @PostMapping("/update/{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return userService.update(userDTO);
    }

}
