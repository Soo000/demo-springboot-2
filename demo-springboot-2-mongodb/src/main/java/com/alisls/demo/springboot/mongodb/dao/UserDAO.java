package com.alisls.demo.springboot.mongodb.dao;

import com.alisls.demo.springboot.mongodb.entity.UserDO;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/9/20
 */
@Component
public class UserDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(UserDO userDO) {
        this.mongoTemplate.save(userDO);
    }

    public List<UserDO> queryUserDOListByName(String name) {
        Query query = Query.query(Criteria.where("name").is(name));
        return this.mongoTemplate.find(query, UserDO.class);
    }

    public List<UserDO> queryUserDOListByName(Integer page, Integer rows) {
        Query query = new Query().limit(rows).skip((page - 1) * rows);
        return this.mongoTemplate.find(query, UserDO.class);
    }

    public UpdateResult update(UserDO person) {
        Query query = Query.query(Criteria.where("id").is(person.getId()));
        Update update = Update.update("age", person.getAge());
        return this.mongoTemplate.updateFirst(query, update, UserDO.class);
    }

    public DeleteResult deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, UserDO.class);
    }

}
