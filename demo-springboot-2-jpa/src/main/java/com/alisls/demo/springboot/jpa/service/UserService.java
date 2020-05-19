package com.alisls.demo.springboot.jpa.service;

import com.alisls.demo.springboot.jpa.dto.RoleDTO;
import com.alisls.demo.springboot.jpa.dto.UserDTO;
import com.alisls.demo.springboot.jpa.dto.UserRoleDTO;
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
public interface UserService {

    UserDTO getById(Long id);

    UserDTO getByUsername(String username);

    UserDTO save(UserDTO userDTO);

    UserRoleDTO saveUserAndRole(UserRoleDTO userRoleDTO);

    UserDTO update(UserDTO userDTO);


}
