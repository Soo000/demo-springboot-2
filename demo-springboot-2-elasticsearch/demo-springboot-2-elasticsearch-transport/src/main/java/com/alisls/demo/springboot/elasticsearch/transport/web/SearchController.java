package com.alisls.demo.springboot.elasticsearch.transport.web;

import com.alisls.demo.springboot.elasticsearch.transport.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Search Controller
 *
 * @author Ke Wang
 */
@RestController
public class SearchController {

    /**
     * 检索管理
     *
     * @param index
     * @param queryStr
     * @return
     */
    @GetMapping(value = "/{index}/_search")
    public String searchDocument(@PathVariable("index") String index,
                                 @RequestParam(value = "q", required = true) String queryStr) {
        SearchService searcher = new SearchService();
        String result = searcher.query(index, queryStr);
        return result;
    }

}
