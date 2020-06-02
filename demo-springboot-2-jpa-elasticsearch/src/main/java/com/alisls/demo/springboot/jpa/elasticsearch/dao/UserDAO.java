package com.alisls.demo.springboot.jpa.elasticsearch.dao;

import com.alisls.demo.springboot.jpa.elasticsearch.pojo.UserDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * UserDAO
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
public interface UserDAO extends ElasticsearchRepository<UserDO, Long> {

    UserDO findByUsername(String username);

}
