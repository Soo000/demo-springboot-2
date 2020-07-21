package com.alisls.demo.springboot.elasticsearch.transport.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AggregationDemo {
	
	private static Logger logger = LogManager.getRootLogger();

	public static void main(String[] args) {
		try (TransportClient client = InitDemo.getClient();) {
			// 1、创建search请求
			SearchRequest searchRequest = new SearchRequest("bank"); 
			
			// 2、用SearchSourceBuilder来构造查询请求体（请仔细查看它的方法，构造各种查询的方法都在这）
			SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
			sourceBuilder.size(0); 

			//添加聚合(做terms聚合，按照age分组，聚合后的名称为by_age，分组按照average_balance排序)
			TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_age")
			        .field("age").order(BucketOrder.aggregation("average_balance", true));
			//指定下级聚合（聚合类型为avg、按照balance取平均值、聚合后的名称为average_balance）
			aggregation.subAggregation(AggregationBuilders.avg("average_balance")
			        .field("balance"));

			sourceBuilder.aggregation(aggregation);
			searchRequest.source(sourceBuilder);
			
			//3、发送请求		
			SearchResponse searchResponse = client.search(searchRequest).get();
				
			//4、处理响应
			if(RestStatus.OK.equals(searchResponse.status())) {		//获取搜索结果状态
				Aggregations aggregations = searchResponse.getAggregations();	//获取response中所有聚合结果
				Terms byAgeAggregation = aggregations.get("by_age"); 	//获取by_age聚合
				logger.info("aggregation by_age 结果");
				logger.info("docCountError: " + byAgeAggregation.getDocCountError());
				logger.info("sumOfOtherDocCounts: " + byAgeAggregation.getSumOfOtherDocCounts());
				logger.info("------------------------------------");
				for(Bucket buck : byAgeAggregation.getBuckets()) {
					logger.info("key: " + buck.getKeyAsNumber());
					logger.info("docCount: " + buck.getDocCount());
					//logger.info("docCountError: " + buck.getDocCountError());
					//取子聚合
					Avg averageBalance = buck.getAggregations().get("average_balance"); 

					logger.info("average_balance: " + averageBalance.getValue());
					logger.info("------------------------------------");
				}
				//直接用key 来去分组
				/*Bucket elasticBucket = byCompanyAggregation.getBucketByKey("24"); 
				Avg averageAge = elasticBucket.getAggregations().get("average_age"); 
				double avg = averageAge.getValue();*/
				
			}
			
		} catch (IOException | InterruptedException | ExecutionException e) {
			logger.error(e);
		}
	}
}
