package com.alisls.demo.elasticsearch.high.service.impl;

import com.alisls.demo.elasticsearch.high.constant.IndexConstant;
import com.alisls.demo.elasticsearch.high.dto.BlogDTO;
import com.alisls.demo.elasticsearch.high.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文档服务
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
@Service("documentService")
@NoArgsConstructor
public class DocumentServiceImpl<T, ID> extends SearchServiceImpl<T> implements DocumentService<T, ID> {

    public ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 索引一段文档，入参为Json字符串
     *
     * @param indexName 索引名称
     * @param json 索引数据
     * @return 索引结果
     * @throws IOException 索引异常
     */
    @Override
    public String saveDocument(String indexName, String id, String json) throws IOException {
        if (id == null) {
            UUID uuid = UUID.randomUUID();
            id = uuid.toString();
        }
        IndexRequest indexRequest = new IndexRequest(indexName, IndexConstant.DEFAULT_DOC_TYPE, id)
                .source(json, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getResult().name();
    }

    /**
     *
     * @param indexName 索引名称
     * @param id 文档ID
     * @param doc 泛型对象
     * @return 文档
     * @throws IOException 异常
     */
    @Override
    public <S extends T> S saveDocument(String indexName, ID id, S doc) throws IOException {
        String strId = "";
        if (id == null) {
            UUID uuid = UUID.randomUUID();
            strId = uuid.toString();
        } else {
            strId = id.toString();
        }

        String json = objectMapper.writeValueAsString(doc);
        IndexRequest indexRequest = new IndexRequest(indexName, IndexConstant.DEFAULT_DOC_TYPE, strId)
                .source(json, XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = indexResponse.getResult();
        if ("created".equalsIgnoreCase(result.getLowercase())) {
            return doc;
        }
        return null;
    }

    /**
     * 索引一段文档，入参为Map
     * @param indexName
     * @param jsonMap
     * @return
     * @throws IOException
     */
    /*private String saveDocument(String indexName, String id, Map<String, Object> jsonMap) throws IOException {
        if (id == null) {
            UUID uuid = UUID.randomUUID();
            id = uuid.toString();
        }
        IndexRequest indexRequest = new IndexRequest(indexName)
                .id(id)
                .source(jsonMap);

        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getResult().name();
    }*/

    /**
     * 批量插入文档
     * @param indexName
     * @return
     */
    /*@Override
    public <S extends T> T batchSaveDocument(String indexName, List<T> docs) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        List<IndexRequest> requests = generateRequests(indexName);
        for (IndexRequest indexRequest : requests) {
            bulkRequest.add(indexRequest);
        }
        restHighLevelClient.bulk(bulkRequest);
        return null;
    }*/

    /*public List<IndexRequest> generateRequests(String indexName) throws IOException {
        List<IndexRequest> requests = new ArrayList<>();
        requests.add(generateNewsRequest(indexName, "中印边防军于拉达克举行会晤 强调维护边境和平"));
        requests.add(generateNewsRequest(indexName, "费德勒收郑泫退赛礼 进决赛战西里奇"));
        requests.add(generateNewsRequest(indexName, "欧文否认拿动手术威胁骑士 兴奋全明星联手詹皇"));
        requests.add(generateNewsRequest(indexName, "皇马官方通告拉莫斯伊斯科伤情 将缺阵西甲关键战"));
        return requests;
    }*/

    public IndexRequest generateNewsRequest(String indexName, String title) throws IOException {
        IndexRequest indexRequest = new IndexRequest(indexName, "_doc");
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setTitle(title);
        String source = objectMapper.writeValueAsString(blogDTO);
        indexRequest.source(source, XContentType.JSON);
        return indexRequest;
    }

}
