package com.alisls.demo.springboot.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MyBatis Plus 应用
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
@SpringBootApplication
@MapperScan(basePackages = "com.alisls.demo.springboot.mybatis.plus.dao")
public class MyBatisPlusApp {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApp.class, args);
    }

}
