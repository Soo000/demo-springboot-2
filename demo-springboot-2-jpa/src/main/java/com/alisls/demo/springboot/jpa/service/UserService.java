package com.alisls.demo.springboot.jpa.service;

import com.alisls.demo.springboot.jpa.dto.UserDTO;
import com.alisls.demo.springboot.jpa.entity.UserDO;
import com.alisls.demo.springboot.jpa.repository.UserRepository;
import com.alisls.demo.springboot.jpa.util.IdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 *
 * @author Ke Wang
 */
@Service("userService")
@AllArgsConstructor
@Log4j2
public class UserService {

    private final IdWorker idWorker;

    private final UserRepository userRepository;

    public UserDTO getById(Long id) {
        UserDTO userDTO = new UserDTO();
        userRepository.findById(id).ifPresent(userDO -> {
            BeanUtils.copyProperties(userDO, userDTO);
        });
        return userDTO;
    }

    public UserDTO getByUsername(String username) {
        UserDO userDO = userRepository.findByCond(username);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }


    public UserDTO save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(idWorker.nextId());

        UserDO newUserDO = userRepository.save(userDO);
        userDTO.setId(newUserDO.getId());

        return userDTO;
    }

    public UserDTO update(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        UserDO newUserDO = userRepository.save(userDO);
        if (log.isDebugEnabled()) {
            log.debug("更新用户成功，用户数据：{}", newUserDO);
        }
        return userDTO;
    }

}
