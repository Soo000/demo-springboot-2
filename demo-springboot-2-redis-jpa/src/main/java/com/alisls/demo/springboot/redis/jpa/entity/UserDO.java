package com.alisls.demo.springboot.redis.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "my_user")
@Data
public class UserDO {

    @Id
    @Column(name = "user_id")
    private Integer userId;
    private String username;
    private Integer age;

}
