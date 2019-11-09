package com.alisls.demo.springboot.shiro.config;

import com.alisls.demo.springboot.shiro.realms.MyRealm;
import com.alisls.demo.springboot.shiro.session.MySessionManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    /**
     * 凭证匹配器
     * 当用户密码校验交给 Shiro 的 SimpleAuthenticationInfo 进行处理，对凭证进行校验使用的类
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义 realm
     * @return
     */
    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        // 设置凭证匹配器
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    /**
     * RedisManager
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(redisPort);
        return redisManager;
    }

    /**
     * RedisSessionDAO 是 shiro 的 Session DAO 层，使用 shiro-redis 插件实现
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * SessionManager
     * 从 SessionManager 中获取 SessionId
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        // 设置 SessionDAO
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

    /**
     * RedisCacheManager
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * SecurityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置自定义 realm
        securityManager.setRealm(myRealm());
        // 设置 session 管理器
        securityManager.setSessionManager(sessionManager());
        // 设置 cache 管理器
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 游客，开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        // 用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        // 管理员，需要角色权限 “admin”
        filterChainDefinitionMap.put("/role/list", "roles[admin]");
        // 开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");
        // 其余接口一律拦截
        // 主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");

        return shiroFilterFactoryBean;
    }

    /**
     * 开启 shiro aop 注解
     * @return
     */
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
