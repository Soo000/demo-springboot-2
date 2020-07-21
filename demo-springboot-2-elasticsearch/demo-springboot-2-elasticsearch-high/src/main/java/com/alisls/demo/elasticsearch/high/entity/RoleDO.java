package com.alisls.demo.elasticsearch.high.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 角色DO
 *
 * @author Ke Wang
 * @date 2020/6/12
 */
@Getter
@Setter
@ToString
public class RoleDO {

    private Long id;

    private String roleCode;

    private String roleName;

    private Integer deleted;

    private Date gmtModified;

    private Date gmtCreate;

    private Integer tenantId;

}
