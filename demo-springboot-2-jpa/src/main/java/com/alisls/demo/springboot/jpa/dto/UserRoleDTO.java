package com.alisls.demo.springboot.jpa.dto;

import lombok.*;

import java.io.Serializable;

/**
 * 用户和角色
 *
 * @author Ke Wang
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleDTO implements Serializable {

    private UserDTO userDTO;
    private RoleDTO roleDTO;

}
