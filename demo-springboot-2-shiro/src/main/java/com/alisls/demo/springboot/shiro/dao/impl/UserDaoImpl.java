package com.alisls.demo.springboot.shiro.dao.impl;

import com.alisls.demo.springboot.shiro.dao.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public String getPasswordByUsername(String username) {
        String sql = "select password from t_user where username = ?";
        String password = jdbcTemplate.queryForObject(sql, String.class, username);
        return password;
    }

    @Override
    public List<String> getRolesByUsername(String username) {
        String sql = "select role_code from t_user u, t_user_role r where u.username = r.username and u.username = ?";
        List<String> roles = jdbcTemplate.queryForList(sql, String.class, username);
        return roles;
    }

}
