package com.alisls.demo.elasticsearch.high.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 评论实体
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
@Getter
@Setter
@ToString
public class CommentDO {

    private Long id;
    private String username;
    private String comment;

}
