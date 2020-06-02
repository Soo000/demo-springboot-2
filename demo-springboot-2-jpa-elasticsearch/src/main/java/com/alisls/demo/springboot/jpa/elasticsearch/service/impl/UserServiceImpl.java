package com.alisls.demo.springboot.jpa.elasticsearch.service.impl;

import com.alisls.demo.springboot.jpa.elasticsearch.dao.UserDAO;
import com.alisls.demo.springboot.jpa.elasticsearch.dto.UserDTO;
import com.alisls.demo.springboot.jpa.elasticsearch.pojo.UserDO;
import com.alisls.demo.springboot.jpa.elasticsearch.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * UserService实现
 *
 * @author Ke Wang
 * @date 2020/6/2
 */
@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public UserDTO getByUsername(String username) {
        UserDTO userDTO = new UserDTO();

        UserDO userDO = userDAO.findByUsername(username);
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        if (userDTO != null) {
            BeanUtils.copyProperties(userDTO, userDO);
            userDO.setGmtCreate(new Date());
            userDAO.save(userDO);

            userDTO.setId(userDO.getId());
            userDTO.setGmtCreate(userDO.getGmtCreate());
        }
        return userDTO;
    }

}
