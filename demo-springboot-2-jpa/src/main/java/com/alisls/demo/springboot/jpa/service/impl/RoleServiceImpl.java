package com.alisls.demo.springboot.jpa.service.impl;

import com.alisls.demo.springboot.jpa.dto.RoleDTO;
import com.alisls.demo.springboot.jpa.entity.RoleDO;
import com.alisls.demo.springboot.jpa.repository.RoleRepository;
import com.alisls.demo.springboot.jpa.service.RoleService;
import com.alisls.demo.springboot.jpa.util.IdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 角色Service
 *
 * @author Ke Wang
 */
@Service("roleService")
@AllArgsConstructor
@Log4j2
public class RoleServiceImpl implements RoleService {

    private final IdWorker idWorker;

    private final RoleRepository roleRepository;

    @Override
    public RoleDTO getById(Long id) {
        RoleDTO roleDTO = new RoleDTO();
        roleRepository.findById(id).ifPresent(roleDO -> {
            BeanUtils.copyProperties(roleDO, roleDTO);
        });
        return roleDTO;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(roleDTO, roleDO);
        roleDO.setId(idWorker.nextId());

        RoleDO newRoleDO = roleRepository.save(roleDO);
        roleDTO.setId(newRoleDO.getId());

        return roleDTO;
    }

    @Override
    public RoleDTO update(RoleDTO roleDTO) {
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(roleDTO, roleDO);
        RoleDO newRoleDO = roleRepository.save(roleDO);
        if (log.isDebugEnabled()) {
            log.debug("更新用户成功，用户数据：{}", newRoleDO);
        }
        return roleDTO;
    }
    
}
