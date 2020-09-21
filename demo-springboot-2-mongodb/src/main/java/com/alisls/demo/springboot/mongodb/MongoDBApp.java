package com.alisls.demo.springboot.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 描述信息
 *
 * @author Ke Wang
 * @date 2020/9/20
 */
@SpringBootApplication
@EntityScan("com.alisls.demo.springboot.mongodb.entity") // 扫描实体类
public class MongoDBApp {

    public static void main(String[] args) {
        SpringApplication.run(MongoDBApp.class, args);
    }


}
