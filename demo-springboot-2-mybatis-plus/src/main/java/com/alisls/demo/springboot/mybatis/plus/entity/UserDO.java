package com.alisls.demo.springboot.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class UserDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String lastName;

    private Integer age;

    private Integer gender;

    @TableLogic
    private Integer deleted;

    private Integer tenantId;

}
