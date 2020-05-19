package com.alisls.demo.springboot.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色DO
 *
 * @author Ke Wang
 */
@Entity
@Table(name = "sys_role")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleDO extends BaseDO {

    @Id
    private Long id;

    private String roleCode;

    private String roleName;

    //private Short deleted;

}
