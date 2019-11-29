package com.alisls.demo.springboot.jpa.elasticsearch.query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoQueryTest {
	
	@Autowired
	DemoQuery demoQuery;
	
	@Test
	public void queryAllTest() {
		demoQuery.findAll();
	}
	
	@Test
	public void queryByPriceTest() {
		demoQuery.findByPriceBetween();
	}

}
