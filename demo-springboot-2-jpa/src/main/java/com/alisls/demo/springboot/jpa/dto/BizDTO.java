package com.alisls.demo.springboot.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务DTO
 *
 * @author Ke Wang
 * @date 2020/11/6
 */
@Getter
@Setter
@ToString
public class BizDTO implements Serializable {

    private Long id;

    private Date updateTime;

    private Date createTime;

}
