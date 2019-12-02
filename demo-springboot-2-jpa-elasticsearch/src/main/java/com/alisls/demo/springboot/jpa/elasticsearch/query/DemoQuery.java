package com.alisls.demo.springboot.jpa.elasticsearch.query;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms.Bucket;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alisls.demo.springboot.jpa.elasticsearch.dao.ItemRepository;
import com.alisls.demo.springboot.jpa.elasticsearch.pojo.Item;

@Component
public class DemoQuery {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
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
	
	public void search() {
		QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "小米手机");
		Iterable<Item> iterables = itemRepository.search(matchQueryBuilder);
		System.out.println(iterables);
	}
	
	/**
	 * 使用 Repository 原生查询
	 */
	public void query() {
		// 原生查询构建器
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		
		// 构建查询条件
		QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "华为");
		nativeSearchQueryBuilder.withQuery(matchQueryBuilder);
		
		// 结果过滤
		FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(new String[] {"title"}, null);
		nativeSearchQueryBuilder.withSourceFilter(fetchSourceFilter);
		
		// 排序
		nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
		
		// 分页
		Pageable pageable = PageRequest.of(0, 5);
		nativeSearchQueryBuilder.withPageable(pageable);
		
		Page<Item> page = itemRepository.search(nativeSearchQueryBuilder.build());
		int totalPages = page.getTotalPages();
		long totalElements = page.getTotalElements();
		List<Item> items = page.getContent();
		if (!CollectionUtils.isEmpty(items)) {
			items.stream().forEach(item -> {
				System.out.println(item);
			});
		}
		
		System.out.println("总页数：" + totalPages + " 总记录数： " + totalElements);
	}
	
	/**
	 * 聚合查询
	 */
	public void aggregation() {
		// 原生查询构建器
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
		
		// 聚合名称
		String aggName = "popularBrand";
		// 聚合
		nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(aggName).field("brand"));
		
		// 查询并返回带聚合结果
		AggregatedPage<Item> result = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), Item.class);
		
		// 解析聚合
		Aggregations aggregations = result.getAggregations();

		// 获取指定名称得聚合
		StringTerms agg = aggregations.get(aggName);
		// 获取聚合结果桶
		List<Bucket> buckets = agg.getBuckets();
		buckets.forEach(bucket -> {
			System.out.println(bucket.getKeyAsString() + " " + bucket.getDocCount());
		});
	}
	
}
