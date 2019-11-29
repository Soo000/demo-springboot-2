package com.alisls.demo.springboot.jpa.elasticsearch.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alisls.demo.springboot.jpa.elasticsearch.dao.ItemRepository;
import com.alisls.demo.springboot.jpa.elasticsearch.pojo.Item;

@Component
public class DemoQuery {

	@Autowired
	private ItemRepository itemRepository;
	
	public void findAll() {
		Iterable<Item> all = itemRepository.findAll();
		for (Item item: all) {
			System.out.println(item);
		}
	}
	
	public void findByPriceBetween() {
		List<Item> items = itemRepository.findByPriceBetween(1000.0, 8000.0);
		for (Item item: items) {
			System.out.println(item);
		}
	}	
	
}
