package com.alisls.demo.springboot.jpa.elasticsearch.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String lastName;

    private Integer age;

    private Integer gender;

    private Date gmtModified;

    private Date gmtCreate;

}
