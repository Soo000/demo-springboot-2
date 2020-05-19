package com.alisls.demo.springboot.swagger.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseDTO {

    private Date gmtCreate;
    private Date gmtModified;

}
