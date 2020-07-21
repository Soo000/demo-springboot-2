package com.alisls.demo.elasticsearch.high.web;

import com.alisls.demo.elasticsearch.high.dto.UserDTO;
import com.alisls.demo.elasticsearch.high.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 用户管理
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public UserDTO save(@RequestBody UserDTO userDTO) {
        try {
            userDTO = userService.saveUser(userDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    @GetMapping("/getById/{id}")
    public UserDTO getById(@PathVariable String id) {
        UserDTO userDTO = null;
        try {
            userDTO = userService.getById("sys_user", id, UserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    @GetMapping("/getByUsername/{username}")
    public List<UserDTO> getByUsername(@PathVariable String username) {
        try {
            return userService.getByUsername(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getByUsercodeAndUsername/{userCode}/{username}")
    public List<UserDTO> getByUserCodeAndUsername(@PathVariable String userCode,
                                                  @PathVariable String username) {
        try {
            return userService.getByUserCodeAndUsername(userCode, username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
