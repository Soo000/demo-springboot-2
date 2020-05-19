package com.alisls.demo.springboot.swagger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel(description = "Long集合DTO")
public class LongListDTO {

    @ApiModelProperty(
            value = "Long型id集合(-9223372036854775808~9223372036854775807)",
            required = true,
            example = "[-9223372036854774999,9223372036854774999]")
    private List<Long> ids;

}
