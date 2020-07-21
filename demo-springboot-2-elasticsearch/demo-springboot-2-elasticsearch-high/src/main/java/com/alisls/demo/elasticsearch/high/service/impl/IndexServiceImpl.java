package com.alisls.demo.elasticsearch.high.service.impl;

import com.alisls.demo.elasticsearch.high.service.IndexService;
import lombok.AllArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

/**
 * 索引服务
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
@Service("indexService")
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final RestHighLevelClient restHighLevelClient;

}
