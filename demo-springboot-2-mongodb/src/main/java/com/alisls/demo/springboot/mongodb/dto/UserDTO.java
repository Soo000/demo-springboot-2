package com.alisls.demo.springboot.mongodb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/9/20
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private Integer age;

}
