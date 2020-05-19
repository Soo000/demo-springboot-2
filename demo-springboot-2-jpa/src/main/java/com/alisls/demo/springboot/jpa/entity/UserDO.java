package com.alisls.demo.springboot.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户DO
 *
 * @author Ke Wang
 */
@Entity
@Table(name = "sys_user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDO extends BaseDO {

    @Id
    private Long id;

    private String username;

    private String nickname;

    private String email;

    private Integer age;

    private String password;

    //private Date birthdate;

    //private Short deleted;

}
