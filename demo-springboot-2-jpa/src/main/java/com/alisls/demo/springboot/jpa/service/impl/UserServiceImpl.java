package com.alisls.demo.springboot.jpa.service.impl;

import com.alisls.demo.springboot.jpa.dto.RoleDTO;
import com.alisls.demo.springboot.jpa.dto.UserDTO;
import com.alisls.demo.springboot.jpa.dto.UserRoleDTO;
import com.alisls.demo.springboot.jpa.entity.UserDO;
import com.alisls.demo.springboot.jpa.repository.UserRepository;
import com.alisls.demo.springboot.jpa.service.RoleService;
import com.alisls.demo.springboot.jpa.service.UserService;
import com.alisls.demo.springboot.jpa.util.IdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户Service
 *
 * @author Ke Wang
 */
@Service("userService")
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final IdWorker idWorker;

    private final UserRepository userRepository;

    private final RoleService roleService;

    @Override
    public UserDTO getById(Long id) {
        UserDTO userDTO = new UserDTO();
        userRepository.findById(id).ifPresent(userDO -> {
            BeanUtils.copyProperties(userDO, userDTO);
        });
        return userDTO;
    }

    @Override
    public UserDTO getByUsername(String username) {
        UserDO userDO = userRepository.findByCond(username);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);
        userDO.setId(idWorker.nextId());

        UserDO newUserDO = userRepository.save(userDO);
        userDTO.setId(newUserDO.getId());

        return userDTO;
    }

    /**
     * 保存用户和角色（该方法只用于测试全局事务配置，不用于实际业务）
     */
    @Override
    public UserRoleDTO saveUserAndRole(UserRoleDTO userRoleDTO) {
        // 保存用户
        this.save(userRoleDTO.getUserDTO());
        log.info("【测试全局事务配置】保存用户成功，用户数据：{}", userRoleDTO.getUserDTO());

        // 模拟抛出异常
        if (userRoleDTO.getUserDTO().getId() != null) {
            log.info("【测试全局事务配置】模拟抛出异常");
            throw new RuntimeException("模拟抛出异常");
        }

        // 保存角色
        RoleDTO roleDTO = roleService.save(userRoleDTO.getRoleDTO());
        log.info("【测试全局事务配置】保存角色成功，角色数据：{}", userRoleDTO.getRoleDTO());

        return userRoleDTO;
    }

    @Override
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
