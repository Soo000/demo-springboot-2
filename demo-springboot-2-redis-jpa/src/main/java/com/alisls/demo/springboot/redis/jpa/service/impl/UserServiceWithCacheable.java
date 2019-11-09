package com.alisls.demo.springboot.redis.jpa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alisls.demo.springboot.redis.jpa.dto.UserDTO;
import com.alisls.demo.springboot.redis.jpa.entity.UserDO;
import com.alisls.demo.springboot.redis.jpa.repository.UserRepository;
import com.alisls.demo.springboot.redis.jpa.service.UserService;

@Service("userServiceWithCacheable")
@CacheConfig(cacheNames = "my_user")
public class UserServiceWithCacheable implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * key ="#p0" 表示以第1个参数作为 key
     */
    @Override
    @Cacheable(value="my_user", key ="#p0")
    public UserDTO findById(Integer userId) {
        UserDO userDO = userRepository.findById(userId).orElse(null);
        UserDTO userDTO = new UserDTO();
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> findByAgeLessThan(Integer age) {
        return null;
    }

    @Override
    @Cacheable
    //@Cacheable(value = "my_user", keyGenerator = "wiselyKeyGenerator")
    public List<UserDTO> findAll() {
        List<UserDO> users = userRepository.findAll();
        if (users != null && !users.isEmpty()) {
            List<UserDTO> myUsers = new ArrayList<UserDTO>();
            for (UserDO userDO: users) {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userDO, userDTO);
                myUsers.add(userDTO);
            }
            return myUsers;
        }
        return null;
    }

    @Override
    @CachePut(key = "#userDTO.userId")
    //@Transactional
    public UserDTO save(UserDTO userDTO) throws RuntimeException {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO = userRepository.save(userDO);

        UserDTO resultDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, resultDTO);
        return resultDTO;
    }

    /**
     * 执行该函数时，将清除以 userService 的缓存，【cacheNames = "userService"】<br>
     * 也可指定清除的key 【@CacheEvict(value="abc")】
     */
    @Override
    @CacheEvict(value = "my_user")
    public void clearAllCache() {
    }

    @Override
    @CacheEvict(value="user", key ="#p0")
    public void clear(Integer userId) {
    }

}
