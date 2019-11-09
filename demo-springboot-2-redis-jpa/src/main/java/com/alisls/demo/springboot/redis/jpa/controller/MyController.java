package com.alisls.demo.springboot.redis.jpa.controller;


import com.alisls.demo.springboot.redis.jpa.common.GeneralResponse;
import com.alisls.demo.springboot.redis.jpa.dto.UserDTO;
import com.alisls.demo.springboot.redis.jpa.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/my")
public class MyController {

    @Resource(name = "userServiceWithCacheable")
    private UserService userService;

    @GetMapping("/findById/{userId}")
    public GeneralResponse<UserDTO> findById(@PathVariable Integer userId) {
        GeneralResponse generalResponse = new GeneralResponse();
        UserDTO userDTO = userService.findById(userId);
        generalResponse.setData(userDTO);
        return generalResponse;
    }

    @GetMapping("/findAll")
    public GeneralResponse findAll() {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(userService.findAll());
        return generalResponse;
    }

    @GetMapping("/findByAgeLessThan")
    public GeneralResponse findByAgeLessThan(@RequestParam Integer age) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(userService.findByAgeLessThan(age));
        return generalResponse;
    }

    @PostMapping("/add")
    public GeneralResponse add(@RequestBody UserDTO userDTO) {
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setData(userService.save(userDTO));
        return generalResponse;
    }

}
