package com.alisls.demo.elasticsearch.high.dto;

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
public class BlogDTO {

    private Long id;
    private String title;
    private String content;

}
