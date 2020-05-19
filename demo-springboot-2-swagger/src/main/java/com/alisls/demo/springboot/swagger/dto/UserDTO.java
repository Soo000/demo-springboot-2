package com.alisls.demo.springboot.swagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "userDTO", description = "用户DTO")
@Getter
@Setter
public class UserDTO {

    @ApiModelProperty(value = "用户标识", example = "1234567890123456789", position = 0)
    private Long id;

    @ApiModelProperty(value = "用户名称", required = true, example = "张三")
    private String name;

    @ApiModelProperty(value = "用户年龄", example = "30")
    private Integer age;

}
