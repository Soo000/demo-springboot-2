package com.alisls.demo.springboot.jpa.elasticsearch.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alisls.demo.springboot.jpa.elasticsearch.dao.ItemRepository;
import com.alisls.demo.springboot.jpa.elasticsearch.pojo.Item;

@Component
public class DemoDocument {

	@Autowired
	private ItemRepository itemRepository;
	
	public void save() {
		Item item = new Item();
		item.setId(2L);
		item.setTitle("2019年华为Mate30");
		item.setBrand("华为");
		item.setPrice(5800.00);
		item.setCategory("手机/通讯");
		item = itemRepository.save(item);
	}
	
}
