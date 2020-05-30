package com.alisls.demo.springboot.mybatis.plus.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户DTO
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;
    private String username;
    private String lastName;
    private Integer age;
    private Integer gender;
    private Integer deleted;
    private Integer tenantId;

}
