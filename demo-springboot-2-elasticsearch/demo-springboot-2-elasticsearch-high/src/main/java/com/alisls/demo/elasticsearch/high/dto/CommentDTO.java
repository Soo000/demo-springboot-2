package com.alisls.demo.elasticsearch.high.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class CommentDTO {

    private Long id;
    private String username;
    private String comment;

    @JsonProperty("blog_comments_relation")
    private BlogCommentsRelation blogCommentsRelation;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    class BlogCommentsRelation {
        private String name;
        private String parent;
    }

}
