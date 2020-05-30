package com.alisls.demo.springboot.mybatis.plus.service.impl;

import com.alisls.demo.springboot.mybatis.plus.dto.UserAddrDTO;
import com.alisls.demo.springboot.mybatis.plus.mapper.UserMapper;
import com.alisls.demo.springboot.mybatis.plus.dto.UserDTO;
import com.alisls.demo.springboot.mybatis.plus.entity.UserDO;
import com.alisls.demo.springboot.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(Long id) {
        UserDTO userDTO = new UserDTO();

        UserDO userDO = userMapper.selectById(id);
        if (userDO != null) {
            BeanUtils.copyProperties(userDO, userDTO);
        }

        return userDTO;
    }

    @Override
    public UserAddrDTO getUserAndAddr(String username) {
        return userMapper.getUserAndAddr(username);
    }

    @Override
    public List<UserDTO> listAll() {
        List<UserDO> list = this.list();
        return null;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setDeleted(0);
        int count = userMapper.insert(userDO);
        userDTO.setId(userDO.getId());
        return userDTO;
    }

}
