package com.alisls.demo.springboot.jpa.repository;

import com.alisls.demo.springboot.jpa.entity.UserDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDO, Integer> {

    UserDO findByUserId(Integer userId);

    @Query(value = "select user_id, username, age from my_user where username = ?", nativeQuery = true)
    UserDO findByCond(String username);

}
