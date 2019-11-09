package com.alisls.demo.springboot.redis.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alisls.demo.springboot.redis.jpa.dto.UserDTO;
import com.alisls.demo.springboot.redis.jpa.entity.UserDO;
import com.alisls.demo.springboot.redis.jpa.repository.UserRepository;
import com.alisls.demo.springboot.redis.jpa.service.UserService;

@Service("userServiceWithRedis")
@CacheConfig(cacheNames = "userServiceWithRedis")
public class UserServiceWithRedis implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceWithJpa.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findById(Integer userId) {
        UserDTO userDTO = new UserDTO();

        UserDO userDO = null;

        String username = (String) redisTemplate.opsForHash().get("ics_user:" + userId, "username");
        if (username != null && username.length() > 0) {
            logger.info("根据 username: {} 从 Redis 中查询到了数据", userId);
            Integer age = (Integer) redisTemplate.opsForHash().get("ics_user:" + userId, "age");
            Integer userIdTmp = (Integer) redisTemplate.opsForHash().get("ics_user:" + userId, "user_id");

            userDO = new UserDO();
            userDO.setUserId(userIdTmp);
            userDO.setUsername(username);
            userDO.setAge(age);
        }

        if (userDO == null) {
            logger.info("根据 userId: {} 没有查询到数据，准备再从数据库中查询", userId);
            userDO = userRepository.findById(userId).get();
        }

        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDO> users = userRepository.findAll();
        if (users != null && !users.isEmpty()) {
            List<UserDTO> myUsers = new ArrayList<UserDTO>();
            for (UserDO userDO: users) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userDO, userDTO);
            }
            return myUsers;
        }
        return null;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return null;
    }

    @Override
    public void clearAllCache() {

    }

    @Override
    public void clear(Integer userId) {

    }

    @Override
    public List<UserDTO> findByAgeLessThan(Integer age) {
        return null;
    }

}
