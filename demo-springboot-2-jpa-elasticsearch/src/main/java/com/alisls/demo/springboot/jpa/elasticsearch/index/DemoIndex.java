package com.alisls.demo.springboot.jpa.elasticsearch.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import com.alisls.demo.springboot.jpa.elasticsearch.pojo.Item;

@Component
public class DemoIndex {

	@Autowired
	private ElasticsearchTemplate template;
	
	/**
	 * 创建索引
	 */
	public void createIndex() {
		// 创建索引库
		boolean result = template.createIndex(Item.class);
		
		// 映射关系
		result = template.putMapping(Item.class);
		
		System.out.println(result);
	}
	
}
