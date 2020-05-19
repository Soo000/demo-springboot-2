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

    private Date gmtModified;

    private Date gmtCreate;

}
