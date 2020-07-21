package com.alisls.demo.elasticsearch.high.service.impl;

import com.alisls.demo.elasticsearch.high.dto.BlogDTO;
import com.alisls.demo.elasticsearch.high.dto.CommentDTO;
import com.alisls.demo.elasticsearch.high.service.BlogService;
import com.alisls.demo.elasticsearch.high.service.DocumentService;
import lombok.AllArgsConstructor;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.HasChildQueryBuilder;
import org.elasticsearch.join.query.HasParentQueryBuilder;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客服务
 *
 * @author Ke Wang
 * @date 2020/7/18
 */
@Service("blogService")
@AllArgsConstructor
public class BlogServiceImpl extends BusiServiceImpl implements BlogService {

    private final String BLOG_COMMENTS_INDEX = "my_blogs";
    private final String PARENT_TYPE = "blog";
    private final String CHILD_TYPE = "comment";

    private final RestHighLevelClient restHighLevelClient;

    private final DocumentService documentService;

    @Override
    public BlogDTO save(BlogDTO blogDTO) throws IOException {
        String id = blogDTO.getId().toString();
        String blogJson = objectToJson(blogDTO);
        documentService.saveDocument(BLOG_COMMENTS_INDEX, id, blogJson);
        return blogDTO;
    }

    @Override
    public List<BlogDTO> listByTitle(String title, int from, int size) throws IOException {
        return null;
    }

    /**
     * 根据父文档标题查询，返回子文档信息(不带父级文档信息)
     * @param title 父文档标题
     * @return 子文档列表
     */
    @Override
    public List<CommentDTO> getByHasParentTitle(String title) throws IOException {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", title);
        HasParentQueryBuilder hasParentQueryBuilder = JoinQueryBuilders.hasParentQuery(PARENT_TYPE, matchQueryBuilder, true);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(hasParentQueryBuilder);

        SearchRequest searchRequest = new SearchRequest(BLOG_COMMENTS_INDEX);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        List<CommentDTO> commentDTOs  = new ArrayList<>();
        for(SearchHit hit : searchHits){
            String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {
                CommentDTO commentDTO = objectMapper.readValue(sourceAsString, CommentDTO.class);
                commentDTOs.add(commentDTO);
            }
        }
        return commentDTOs;
    }

    /**
     * 根据子文档中的评论查询，返回父文档信息
     * @param comment
     * @return
     * @throws IOException
     */
    @Override
    public List<BlogDTO> getByHasChildComment(String comment) throws IOException {
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("comment", comment);
        ScoreMode scoreMode = ScoreMode.Total;
        HasChildQueryBuilder hasChildQueryBuilder = JoinQueryBuilders.hasChildQuery(CHILD_TYPE, matchQueryBuilder, scoreMode);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(hasChildQueryBuilder);

        SearchRequest searchRequest = new SearchRequest(BLOG_COMMENTS_INDEX);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        List<BlogDTO> blogDTOs  = new ArrayList<>();
        for(SearchHit hit : searchHits){
            String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {
                BlogDTO blogDTO = objectMapper.readValue(sourceAsString, BlogDTO.class);
                blogDTOs.add(blogDTO);
            }
        }
        return blogDTOs;
    }

}
