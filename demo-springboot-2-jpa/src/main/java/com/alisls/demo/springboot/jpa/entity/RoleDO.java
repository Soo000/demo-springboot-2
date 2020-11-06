package com.alisls.demo.springboot.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
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

    /**
     * 角色标识
     */
	@Id
	private Long id;

    /**
     * 角色编码
     */
	@Column(nullable = false, length = 10, unique = true)
	private String roleCode;

    /**
     * 父级角色编码
     */
    @Column(nullable = false, length = 10)
	private String parentCode;

    /**
     * 角色名称
     */
    @Column(nullable = false, length = 10)
	private String roleName;

    /**
     * 角色状态
     * state: 0-为生效 1-已生效 2-已作废
     */
    private Integer status;

    /**
     * 排序值
     */
    private float position;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Integer deleted;
	
}
