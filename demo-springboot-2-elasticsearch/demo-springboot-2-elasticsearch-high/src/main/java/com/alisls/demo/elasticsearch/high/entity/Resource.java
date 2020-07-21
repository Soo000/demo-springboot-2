package com.alisls.demo.elasticsearch.high.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限DO
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
@Getter
@Setter
@ToString
public class Resource {

    private Long id;

    private String code;

    private Long parentId;

    private String name;

}
