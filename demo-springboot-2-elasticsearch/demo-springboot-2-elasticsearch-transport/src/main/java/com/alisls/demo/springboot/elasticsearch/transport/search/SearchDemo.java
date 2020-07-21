package com.alisls.demo.springboot.elasticsearch.transport.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 检索Demo
 *
 * @author Ke Wang
 */
public class SearchDemo {

    /**
     * 入口方法 main
     */
    public static void main(String args[]) throws UnknownHostException {
        // 配置信息
        Settings settings = Settings.builder()
                //.put("cluster.name", "my-application") // 指定集群名称
                .put("client.transport.sniff", true)  // 开启自动嗅探，只需要配置一台ES,会自动嗅探到其它ES节点
                .build();
        /**
         嗅探：客户端只需要指定一个ES服务端节点连接信息，连接上之后，如果开启了嗅探机制，
         就会自动拉取服务端各节点的信息到客户端，从而避免我们需要配置一长串服务端连接信息
         */

        // 构建client
        TransportClient client = new PreBuiltTransportClient(settings);
        // 指定ip、port
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.1.116"), 9300)); // 9300为TCP通信端口

        try {
            // 构建request
            SearchRequest request = new SearchRequest();
            request.indices("music");
            request.types("songs");

            // 构建搜索
            SearchSourceBuilder search = new SearchSourceBuilder();
            search.query(QueryBuilders.matchQuery("songName", "take me to your heart"));
            search.timeout(new TimeValue(60, TimeUnit.SECONDS));

            // search放入到request中
            request.source(search);

            // 执行搜索
            SearchResponse response = client.search(request).get();

            // 获取命中的文档
            SearchHits hits = response.getHits();
            SearchHit[] hitArr = hits.getHits();
            System.out.println("搜索到" + hits.totalHits + "个文档");

            // 处理命中的文档
            for (SearchHit hit : hitArr){
                //打印元信息
                System.out.println(hit.getType() + "," + hit.getScore());

                //打印原文档
                String sourceAsStrig = hit.getSourceAsString();
                System.out.println(sourceAsStrig);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
