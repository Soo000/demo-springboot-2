package com.alisls.demo.elasticsearch.high.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
@Getter
@Setter
@ToString
public class UserRoleDTO {

    private String userCode;
    private String roleCode;
}
