package com.alisls.demo.springboot.mybatis.plus.web;

import com.alisls.demo.springboot.mybatis.plus.dto.UserAddrDTO;
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

    @GetMapping("/getUserByUsername/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserAndAddr/{username}")
    public UserAddrDTO getUserAndAddr(@PathVariable String username) {
        return userService.getUserAndAddr(username);
    }

    @PostMapping("/save")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public Boolean removeUser(@PathVariable Long id) {
        return userService.removeById(id);
    }

}
