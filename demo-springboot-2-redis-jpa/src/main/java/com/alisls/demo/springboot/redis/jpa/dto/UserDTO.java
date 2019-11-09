package com.alisls.demo.springboot.redis.jpa.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private Integer userId;
    private String username;
    private Integer age;

}
