package com.alisls.demo.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 的配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 是否启用调试
        //web.debug(true);
    }

    /**
     * 定义密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 访问 /r/r1要有p1权限
                .antMatchers("/r/r1").hasAuthority("p1")
                // 访问 /r/r2要有p2权限
                .antMatchers("/r/r2").hasAuthority("p2")
                // 所有 /r/**的请求必须认证
                .antMatchers("/r/**").authenticated()
                // 其它的可以访问
                .anyRequest().permitAll()
                .and()
                // 允许表单登录
                .formLogin();
                // 登录成功转发地址
                //.successForwardUrl("/login-success");
    }
}
