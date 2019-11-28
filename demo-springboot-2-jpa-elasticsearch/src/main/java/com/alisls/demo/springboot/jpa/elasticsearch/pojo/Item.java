package com.alisls.demo.springboot.jpa.elasticsearch.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "leyou-index", type = "item", shards = 3, replicas = 2)
public class Item {
	
	@Id
	@Field(type = FieldType.Long)
	private Long id;
	
	@Field(type = FieldType.Text, analyzer = "ik_smart")
	private String title;
	
	@Field(type = FieldType.Keyword)
	private String category;
	
	@Field(type = FieldType.Keyword)
	private String brand;
	
	@Field(type = FieldType.Double)
	private Double price;
	
	@Field(type = FieldType.Keyword, index = false)
	private String images;

}
