package com.alisls.demo.elasticsearch.high.service;

import java.io.IOException;
import java.util.List;

/**
 * 文档服务
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
public interface DocumentService<T, ID> extends SearchService<T> {

    /**
     * 保存文档，文档类型为JSON字符串
     * @param indexName 索引名称
     * @param id 文档ID
     * @param json 文档JSON字符串
     * @return 文档
     * @throws IOException 异常
     */
    String saveDocument(String indexName, String id, String json) throws IOException;

    /**
     * 保存文档，文档类型为泛型对象
     * @param indexName 索引名称
     * @param id 文档ID
     * @param doc 泛型对象
     * @return 文档
     * @throws IOException 异常
     */
    <S extends T> S saveDocument(String indexName, ID id, S doc) throws IOException;

    /**
     * 批量保存文档
     * @param indexName 索引名称
     * @return 文档
     * @throws IOException 异常
     */
//    <S extends T> List<T> batchSaveDocument(String indexName, List<T> docs) throws IOException;

}
