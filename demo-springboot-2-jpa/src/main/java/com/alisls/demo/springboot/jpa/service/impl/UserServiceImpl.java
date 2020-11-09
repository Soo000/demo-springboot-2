package com.alisls.demo.springboot.jpa.service.impl;

import com.alisls.demo.springboot.jpa.dto.user.UserDTO;
import com.alisls.demo.springboot.jpa.entity.RoleDO;
import com.alisls.demo.springboot.jpa.entity.UserDO;
import com.alisls.demo.springboot.jpa.repository.UserRepository;
import com.alisls.demo.springboot.jpa.service.RoleService;
import com.alisls.demo.springboot.jpa.service.UserService;
import com.alisls.demo.springboot.jpa.util.IdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public UserDTO getByCode(String code) {
        UserDO userDO = userRepository.findByCode(code);
        log.info("查询到用户DO：{}", userDO);

        if (userDO == null) {
            return null;
        }

        List<RoleDO> roleDOs = userDO.getRoles();
        log.info("查用到用户DO后，再获取用户角色List：{}", roleDOs);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO, "roles");

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
        userDTO.setId(idWorker.nextId());
        BeanUtils.copyProperties(userDTO, userDO);
        UserDO newUserDO = userRepository.save(userDO);
        return userDTO;
    }

    /**
     * 保存用户和角色（该方法只用于测试全局事务配置，不用于实际业务）
     */
    @Override
    public UserDTO saveUserAndRole(UserDTO userDTO) {
        return userDTO;
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
