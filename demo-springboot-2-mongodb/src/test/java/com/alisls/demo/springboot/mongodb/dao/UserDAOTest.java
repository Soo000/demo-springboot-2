package com.alisls.demo.springboot.mongodb.dao;

import com.alisls.demo.springboot.mongodb.entity.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/9/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void save() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setUsername("我是SpringBoot");
        userDAO.save(userDO);
    }

}