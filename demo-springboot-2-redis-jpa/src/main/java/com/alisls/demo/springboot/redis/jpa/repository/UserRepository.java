package com.alisls.demo.springboot.redis.jpa.repository;


import com.alisls.demo.springboot.redis.jpa.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Integer>, JpaSpecificationExecutor<UserDO> {

    UserDO findByUserId(Integer userId);

}
