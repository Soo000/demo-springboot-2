package com.alisls.demo.springboot.jpa.repository;

import com.alisls.demo.springboot.jpa.entity.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色Repository
 *
 * @author Ke Wang
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleDO, Long> {

}
