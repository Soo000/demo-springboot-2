package com.alisls.demo.springboot.mybatis.plus.service;

import com.alisls.demo.springboot.mybatis.plus.dto.UserAddrDTO;
import com.alisls.demo.springboot.mybatis.plus.dto.UserDTO;
import com.alisls.demo.springboot.mybatis.plus.entity.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * UserService
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
public interface UserService extends IService<UserDO> {

    UserDTO getUser(Long id);

    UserDTO getUserByUsername(String username);

    UserAddrDTO getUserAndAddr(String username);

    List<UserDTO> listAll();

    UserDTO saveUser(UserDTO userDTO);

}
