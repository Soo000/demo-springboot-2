package com.alisls.demo.elasticsearch.high.web;

import com.alisls.demo.elasticsearch.high.service.DocumentService;
import com.alisls.demo.elasticsearch.high.service.IndexService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 索引服务
 *
 * @author Ke Wang
 * @date 2020/6/17
 */
@RestController
@RequestMapping("/index")
@AllArgsConstructor
@Slf4j
public class DocumentController {

    private final IndexService indexService;

    private final DocumentService documentService;

    /**
     * 索引对一段JSON数据

     * @param indexName 索引名称
     * @param json 要索引的JSON数据
     * @return 结果
     */
    @PostMapping("/doIndex/{indexName}")
    public String doIndex(@PathVariable String indexName, @RequestBody String json) {
        try {
            return documentService.saveDocument(indexName, null, json);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return "failed";
    }

}
