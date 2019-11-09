package com.alisls.demo.springboot.shiro.dao;

import java.util.List;

public interface UserDao {

    /**
     * 根据用户名查询密码
     * @param username
     * @return
     */
    public String getPasswordByUsername(String username);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    List<String> getRolesByUsername(String username);

}
