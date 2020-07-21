package com.alisls.demo.elasticsearch.high.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 博客实体
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
@Getter
@Setter
@ToString
public class BlogDO {

    private Long id;
    private String title;
    private String content;

}
