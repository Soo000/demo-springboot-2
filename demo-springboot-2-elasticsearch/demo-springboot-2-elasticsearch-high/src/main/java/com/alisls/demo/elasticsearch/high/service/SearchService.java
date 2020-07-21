package com.alisls.demo.elasticsearch.high.service;

import java.io.IOException;

/**
 * 搜素服务
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
public interface SearchService<T> {

    /**
     * 根据文档ID查询文档
     * @param indexName 索引名称
     * @param id 文档ID
     * @return 文档信息
     * @throws IOException 异常
     */
    T getById(String indexName, String id, Class<T> type) throws IOException;

    /**
     * 查询所有文档
     * @param indexName 索引名称
     * @return 文档信息
     * @throws IOException
     */
    String list(String indexName) throws IOException;

    /**
     * 分页查询所有文档
     * @param from 起始记录下标
     * @param size 每页记录数据数
     * @param indexName 索引名称
     * @return 文档信息
     * @throws IOException 异常
     */
    String list(int from, int size, String indexName) throws IOException;

}
