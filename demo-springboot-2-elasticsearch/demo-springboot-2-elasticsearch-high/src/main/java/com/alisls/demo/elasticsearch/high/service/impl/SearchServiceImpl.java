package com.alisls.demo.elasticsearch.high.service.impl;

import com.alisls.demo.elasticsearch.high.constant.IndexConstant;
import com.alisls.demo.elasticsearch.high.service.SearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 检索服务
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
@Service("searchService")
@NoArgsConstructor
@Slf4j
public class SearchServiceImpl<T> implements SearchService<T> {

    public RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 根据文档ID查询文档
     * @param indexName 索引名称
     * @param id 文档ID
     * @return 所有文档
     * @throws IOException 异常信息
     */
    @Override
    public T getById(String indexName, String id, Class<T> type) throws IOException {
        GetRequest getRequest = new GetRequest(indexName, IndexConstant.DEFAULT_DOC_TYPE, id);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        if (!getResponse.isExists()) {
            return null;
        }

        String json = getResponse.getSourceAsString();
        T result = objectMapper.readValue(json, type);
        return result;
    }

    /**
     * 查询所有文档
     * @param indexName 索引名称
     * @return 所有文档
     * @throws IOException 异常信息
     */
    @Override
    public String list(String indexName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse.toString();
    }

    /**
     * 分页查询所有
     * @param from 开始下标
     * @param size 查询数量
     * @param indexName 索引名称
     * @return 查询结果
     * @throws IOException 异常
     */
    @Override
    public String list(int from, int size, String indexName) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse.toString();
    }

    @Autowired
    public void setRestHighLevelClient(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

}
