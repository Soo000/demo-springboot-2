package com.alisls.demo.springboot.elasticsearch.transport.web;

import com.alisls.demo.springboot.elasticsearch.transport.config.Settings;
import com.alisls.demo.springboot.elasticsearch.transport.service.IndexService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 索引管理
 *
 * @author Ke Wang
 * @date 2020/6/30
 */
@RestController
@RequestMapping("/index")
@AllArgsConstructor
public class IndexController {

    private final IndexService indexService;

    /**
     * 创建索引
     * @param indexName
     * @return
     */
    @PostMapping("/createIndex/{indexName}")
    public String createIndex(@PathVariable String indexName) {
        indexService.createIndex(indexName);
        return "创建了本地索引文件，索引目录：" + Settings.DATA_DIR;
    }

}
