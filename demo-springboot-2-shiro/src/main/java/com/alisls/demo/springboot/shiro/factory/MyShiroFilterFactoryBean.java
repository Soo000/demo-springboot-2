package com.alisls.demo.springboot.shiro.factory;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 ShiroFilterFactoryBean, 动态添加过滤规则
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroFilterFactoryBean.class);

    private static final String ROLE_STRING = "roles[{0}]";

    @Override
    public void setFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        //did they explicitly state a 'urls' section?  Not necessary, but just in case:
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            //no urls section.  Since this _is_ a urls chain definition property, just assume the
            //default section contains only the definitions:
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }

        // 自定义添加过滤规则，加入以下数据是从数据库中读取的
        Map<String, String[]> permsMap = new HashMap();
        // "/user/**" 需要 "user" 角色才可以访问
        permsMap.put("/user/**", new String[] {"user"});
        // "/doTest2" 需要 "test" 和 "guest" 两个角色才可以访问
        permsMap.put("/user/list", new String[]{"admin", "user"});

        // 生成规则格式
        for (String url: permsMap.keySet()) {
            String[] roles = permsMap.get(url);
            StringBuilder sb = new StringBuilder();
            for (String role: roles) {
                sb.append(role).append(",");
            }

            // 拼接成 roles[role1,role2]
            String str = sb.toString().substring(0, sb.length() -1);
            // 存入 section 中，session 继承 HashMap, key为 url, value 为 角色字符串
            String rolesStr = MessageFormat.format(ROLE_STRING, str);
            logger.info("资源访问规则：{0} = {1}", url, rolesStr);

            section.put(url, rolesStr);
        }

        setFilterChainDefinitionMap(section);
    }

}
