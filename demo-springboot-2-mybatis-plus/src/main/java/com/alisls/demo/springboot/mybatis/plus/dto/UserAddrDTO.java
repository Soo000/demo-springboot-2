package com.alisls.demo.springboot.mybatis.plus.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/5/30
 */
@Getter
@Setter
@ToString
public class UserAddrDTO {

    private Long id;

    private Long userId;

    private String username;

    private String addrName;

}
