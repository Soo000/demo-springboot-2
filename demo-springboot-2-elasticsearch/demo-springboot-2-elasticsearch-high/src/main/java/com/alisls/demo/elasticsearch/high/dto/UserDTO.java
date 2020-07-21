package com.alisls.demo.elasticsearch.high.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户DTO
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

    private Long id;

    private String userCode;

    private String username;

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    private Integer age;

    private Integer gender;

    private Integer deleted;

    private Date gmtModified;

    private Date gmtCreate;

    private Integer tenantId;

}
