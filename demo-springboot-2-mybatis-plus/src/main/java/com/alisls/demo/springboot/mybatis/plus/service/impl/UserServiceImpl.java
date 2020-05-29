package com.alisls.demo.springboot.mybatis.plus.service.impl;

import com.alisls.demo.springboot.mybatis.plus.dao.UserDAO;
import com.alisls.demo.springboot.mybatis.plus.dto.UserDTO;
import com.alisls.demo.springboot.mybatis.plus.entity.UserDO;
import com.alisls.demo.springboot.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService实现
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
@Service("userService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public UserDTO getUser(Long id) {
        UserDTO userDTO = new UserDTO();

        UserDO userDO = userDAO.selectById(id);
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

    @Override
    public List<UserDTO> listAll() {
        return null;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        int count = userDAO.insert(userDO);
        userDTO.setId(userDO.getId());
        return userDTO;
    }

}
