package com.alisls.demo.elasticsearch.high.service.impl;

import com.alisls.demo.elasticsearch.high.dto.UserDTO;
import com.alisls.demo.elasticsearch.high.entity.UserDO;
import com.alisls.demo.elasticsearch.high.service.UserService;
import lombok.AllArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserService实现
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
@Service("userService")
public class UserServiceImpl extends DocumentServiceImpl<UserDTO, Long> implements UserService {

    private final String USER_INDEX_NAME = "sys_user";

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return UserDTO
     */
    @Override
    public List<UserDTO> getByUsername(String username) throws IOException {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("username", username);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(termQueryBuilder);

        SearchRequest searchRequest = new SearchRequest(USER_INDEX_NAME);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        List<UserDTO> userDTOs = new ArrayList<>();
        for(SearchHit hit : searchHits){
            String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {
                UserDTO userDTO = objectMapper.readValue(sourceAsString, UserDTO.class);
                userDTOs.add(userDTO);
            }
        }
        return userDTOs;
    }

    /**
     * 根据用户编码和用户名查询
     * @param userCode 用户编码
     * @param username 用户名
     * @return 用户列表
     * @throws IOException 异常
     */
    @Override
    public List<UserDTO> getByUserCodeAndUsername(String userCode, String username) throws IOException {
        // 用户编码条件
        TermQueryBuilder userCodeTermQueryBuilder = QueryBuilders.termQuery("userCode", userCode);
        // 用户名条件
        TermQueryBuilder usernameTermQueryBuilder = QueryBuilders.termQuery("username", username);

        // 组合用户编码和用户名条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(userCodeTermQueryBuilder);
        boolQueryBuilder.must(usernameTermQueryBuilder);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest(USER_INDEX_NAME);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();

        List<UserDTO> userDTOs = new ArrayList<>();
        for(SearchHit hit : searchHits){
            String sourceAsString = hit.getSourceAsString();
            if (sourceAsString != null) {
                UserDTO userDTO = objectMapper.readValue(sourceAsString, UserDTO.class);
                userDTOs.add(userDTO);
            }
        }
        return userDTOs;
    }

    /**
     * 保存用户
     * @param userDTO 用户DTO
     * @return 用户DTO
     * @throws IOException 异常
     */
    @Override
    public UserDTO saveUser(UserDTO userDTO) throws IOException {
        return saveDocument(USER_INDEX_NAME, userDTO.getId(), userDTO);
    }

}
