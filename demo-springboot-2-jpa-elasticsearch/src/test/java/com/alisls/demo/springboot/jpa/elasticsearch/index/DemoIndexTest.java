package com.alisls.demo.springboot.jpa.elasticsearch.index;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoIndexTest {
	
	@Autowired
	DemoIndex demoIndex;
	
	@Test
	public void createIndexTest() {
		demoIndex.createIndex();
	}

}
