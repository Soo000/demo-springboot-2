package com.alisls.demo.springboot.jpa.elasticsearch.index;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alisls.demo.springboot.jpa.elasticsearch.pojo.Item;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoIndexTest {
	
	//@Autowired
	//private DemoIndex demoIndex;
	
	@Autowired
	ElasticsearchTemplate template;
	
	@Test
	public void createIndexTest() {
		// 创建索引库
		boolean result = template.createIndex(Item.class);
		
		// 映射关系
		result = template.putMapping(Item.class);
		
		System.out.println(result);
	}

}
