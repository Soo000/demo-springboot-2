package com.alisls.demo.springboot.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户DO
 *
 * @author Ke Wang
 */
@Entity
@Table(name = "sys_user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDO extends BaseDO implements Serializable {

	/**
	 * 用户标识
	 */
	@Id
	private Long id;

    /**
     * 用户编码
     */
	@Column(nullable = false, length = 10, unique = true)
	private String code;

    /**
     * 用户名称
     */
    @Column(nullable = false, length = 128, unique = true)
	private String username;

    /**
     * 用户昵称
     */
    @Column(length = 128)
	private String nickname;

    /**
     * 用户手机号
     */
    @Column(length = 32, unique = true)
	private String mobile;

    /**
     * 用户邮箱
     */
    @Column(length = 128, unique = true)
	private String email;

    /**
     * 用户密码
     */
    @Column(columnDefinition = "varchar(128) not null")
	private String password;

    /**
     * 用户角色
     * 注解@ManyToMany说明是一个多对多关系
     * 注解@JoinTable的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
     *
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_code", referencedColumnName = "code"),
            inverseJoinColumns = @JoinColumn(name = "role_code", referencedColumnName = "roleCode")
    )
    List<RoleDO> roles;
	
}
