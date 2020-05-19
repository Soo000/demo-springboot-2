package com.alisls.demo.springboot.swagger.entity;

import com.alisls.demo.springboot.swagger.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class UserDO extends BaseDTO {

    private Long id;
    private String name;
    private Integer age;

}
