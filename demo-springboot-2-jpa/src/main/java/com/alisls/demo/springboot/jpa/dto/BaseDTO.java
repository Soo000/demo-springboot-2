package com.alisls.demo.springboot.jpa.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseDTO
 *
 * @author Ke Wang
 */
@Getter
@Setter
public class BaseDTO implements Serializable {

    private Date gmtModified;

    private Date gmtCreate;

}
