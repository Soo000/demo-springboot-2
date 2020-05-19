package com.alisls.demo.springboot.jpa.config;

import com.alisls.demo.springboot.jpa.util.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jpa配置类
 *
 * @author Ke Wang
 */
@Configuration
public class JpaConfig {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

}
