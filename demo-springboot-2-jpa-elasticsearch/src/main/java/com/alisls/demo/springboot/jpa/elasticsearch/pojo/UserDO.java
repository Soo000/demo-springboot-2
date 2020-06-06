package com.alisls.demo.springboot.jpa.elasticsearch.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 用户DO
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
@Getter
@Setter
@ToString
@Document(indexName = "es_user", type = "_doc", shards = 1, replicas = 1)
public class UserDO {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Text)
    private String username;

    @Field(type = FieldType.Keyword)
    private String lastName;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(type = FieldType.Keyword)
    private Integer gender;

    @Field(type = FieldType.Keyword)
    private Integer age;

    @Field(type = FieldType.Keyword)
    private Integer deleted;

    @Field(type = FieldType.Date)
    private Date gmtModified;

    @Field(type = FieldType.Date)
    private Date gmtCreate;

    @Field(type = FieldType.Keyword)
    private Integer tenantId;

}
