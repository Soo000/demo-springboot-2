package com.alisls.demo.elasticsearch.high.dao;

import com.alisls.demo.elasticsearch.high.entity.UserDO;

/**
 * UserDAO
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
public interface UserDAO {

    UserDO findByUsername(String username);

}
