package com.alisls.demo.elasticsearch.high.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息DTO
 *
 * @author Ke Wang
 * @date 2020/7/17
 */
@Getter
@Setter
@ToString
public class UserInfoDTO {

    private UserDTO userDTO;
    private RoleDTO roleDTO;
    private UserRoleDTO userRoleDTO;

}
