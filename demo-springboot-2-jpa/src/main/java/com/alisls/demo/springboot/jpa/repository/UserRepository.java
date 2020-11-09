package com.alisls.demo.springboot.jpa.repository;

import com.alisls.demo.springboot.jpa.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 用户Repository
 *
 * @author Ke Wang
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO, Long> {

    UserDO findByCode(String code);

    @Query(value = "select user_id, username, age from my_user where username = ?", nativeQuery = true)
    UserDO findByCond(String username);

}
