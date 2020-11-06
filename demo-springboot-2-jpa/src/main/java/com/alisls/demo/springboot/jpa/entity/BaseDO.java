package com.alisls.demo.springboot.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * BaseDO
 *
 * @author Ke Wang
 */
@Getter
@Setter
public class BaseDO {

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

}
