package com.alisls.demo.springboot.mybatis.plus.web;

import com.alisls.demo.springboot.mybatis.plus.dto.UserDTO;
import com.alisls.demo.springboot.mybatis.plus.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

}
