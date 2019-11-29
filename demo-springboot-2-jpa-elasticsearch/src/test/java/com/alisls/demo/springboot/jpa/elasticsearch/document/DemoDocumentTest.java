package com.alisls.demo.springboot.jpa.elasticsearch.document;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alisls.demo.springboot.jpa.elasticsearch.document.DemoDocument;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoDocumentTest {
	
	@Autowired
	DemoDocument demoDocument;
	
	@Test
	public void saveDocumentTest() {
		demoDocument.save();
	}

}
