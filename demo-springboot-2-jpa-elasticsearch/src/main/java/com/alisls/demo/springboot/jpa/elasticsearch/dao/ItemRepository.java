package com.alisls.demo.springboot.jpa.elasticsearch.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.alisls.demo.springboot.jpa.elasticsearch.pojo.Item;

public interface ItemRepository extends ElasticsearchRepository<Item, Long> {
	
	List<Item> findByPriceBetween(Double from, Double to);
	
}
