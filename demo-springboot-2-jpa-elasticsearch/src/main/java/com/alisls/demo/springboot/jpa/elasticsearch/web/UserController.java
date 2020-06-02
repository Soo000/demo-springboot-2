package com.alisls.demo.springboot.jpa.elasticsearch.web;

import com.alisls.demo.springboot.jpa.elasticsearch.dto.UserDTO;
import com.alisls.demo.springboot.jpa.elasticsearch.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getByUsername/{username}")
    public UserDTO getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/saveUser")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

}
