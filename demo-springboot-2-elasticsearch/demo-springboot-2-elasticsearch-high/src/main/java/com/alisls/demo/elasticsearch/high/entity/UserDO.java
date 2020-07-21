package com.alisls.demo.elasticsearch.high.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/**
 * 用户DO
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
@Getter
@Setter
@ToString
public class UserDO {

    private Long id;

    private String username;

    private String userCode;

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    private String mobile;

    private Integer gender;

    private Integer age;

    private Integer deleted;

    private Date gmtModified;

    private Date gmtCreate;

    private Integer tenantId;

    /**
     * 用户角色集合
     */
    private Set<RoleDO> roles;

}
