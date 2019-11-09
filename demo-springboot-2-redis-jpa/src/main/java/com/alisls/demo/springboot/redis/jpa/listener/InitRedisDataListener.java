package com.alisls.demo.springboot.redis.jpa.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alisls.demo.springboot.redis.jpa.repository.UserRepository;

@Component
public class InitRedisDataListener implements ApplicationListener {

    private Logger logger = LoggerFactory.getLogger(InitRedisDataListener.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        /*if (event instanceof ApplicationReadyEvent) {
            logger.info("容器准备好了，准备预热 Redis数据");
            List<UserDO> users = userRepository.findAll();
            if (users != null && !users.isEmpty()) {
                for (UserDO userDO: users) {
                    logger.info("准备预热 ics_user 表数据");
                    redisTemplate.opsForList().leftPush("ics_user:user_id", userDO.getUserId());

                    redisTemplate.opsForHash().put("ics_user:" + userDO.getUserId(), "user_id", userDO.getUserId());
                    redisTemplate.opsForHash().put("ics_user:" + userDO.getUserId(), "username", userDO.getUsername());
                    redisTemplate.opsForHash().put("ics_user:" + userDO.getUserId(), "age", userDO.getAge());
                    logger.info("预热 ics_user 表数据完成");
                }
            }
        }*/
    }

}
