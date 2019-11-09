package com.alisls.demo.springboot.shiro.service;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名查询密码
     * @param username
     * @return
     */
    String getPasswordByUsername(String username);

    /**
     * 根据用户名查询角色
     * @param username
     * @return
     */
    List<String> getRolesByUsername(String username);

}
