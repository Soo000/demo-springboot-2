package com.alisls.demo.springboot.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户地址DO
 *
 * @author Ke Wang
 * @date 2020/5/30
 */
@TableName(value = "tbl_user_addr")
@Getter
@Setter
@ToString
public class UserAddrDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String addrName;

}
