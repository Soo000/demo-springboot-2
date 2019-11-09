package com.alisls.demo.springboot.jpa.repository;

import com.alisls.demo.springboot.jpa.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserDO, Integer> {
}
