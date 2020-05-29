package com.alisls.demo.springboot.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户实体
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
@TableName(value = "tbl_user")
@Getter
@Setter
@ToString
public class UserDO {


    private Long id;
    private String username;
    private String lastName;
    private Integer age;
    private Integer gender;

}
