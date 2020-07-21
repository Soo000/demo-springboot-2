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
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/6/12
 */
@Getter
@Setter
@ToString
@Document(indexName = "es_role", type = "_doc", shards = 1, replicas = 1)
public class RoleDO {

    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(type = FieldType.Keyword)
    private String roleCode;

    @Field(type = FieldType.Text)
    private String roleName;

    @Field(type = FieldType.Keyword)
    private Integer deleted;

    @Field(type = FieldType.Date)
    private Date gmtModified;

    @Field(type = FieldType.Date)
    private Date gmtCreate;

    @Field(type = FieldType.Keyword)
    private Integer tenantId;

}
