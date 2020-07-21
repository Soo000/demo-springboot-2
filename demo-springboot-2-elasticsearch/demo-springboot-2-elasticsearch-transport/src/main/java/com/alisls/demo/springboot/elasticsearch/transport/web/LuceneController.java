package com.alisls.demo.springboot.elasticsearch.transport.web;

import com.alisls.demo.springboot.elasticsearch.transport.lucene.LuceneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/6/30
 */
@RestController
@RequestMapping("/lucene")
@AllArgsConstructor
public class LuceneController {

    private final LuceneService luceneService;

    /**
     * 使用lucene创建索引目录
     *
     * @param indexDir
     * @return
     */
    @PostMapping("/createIndex")
    public String createIndex(@RequestParam String indexDir) {
        luceneService.createIndex(indexDir);
        return "Lucene创建索引目录：" + indexDir;
    }

    /**
     * 使用lucene索引文档
     *
     * @param indexDir
     * @param jsonDoc
     */
    @PostMapping("/indexDoc/{indexDir}")
    public String indexDoc(@PathVariable String indexDir, @RequestBody String jsonDoc) {
        luceneService.indexDoc(indexDir, jsonDoc);
        return "使用lucene索引了文档：" + jsonDoc;
    }

    /**
     * 使用luence查询
     *
     * @param indexDir
     * @param queryStr
     * @return
     */
    @GetMapping("/query/{indexDir}/{queryStr}")
    public String query(@PathVariable String indexDir, @PathVariable String queryStr) {
        return luceneService.query(indexDir, queryStr);
    }

}
