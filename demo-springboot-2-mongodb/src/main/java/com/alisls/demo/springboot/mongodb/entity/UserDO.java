package com.alisls.demo.springboot.mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/9/20
 */
@Getter
@Setter
@ToString
public class UserDO {

    private Long id;
    private String username;
    private Integer age;

}
