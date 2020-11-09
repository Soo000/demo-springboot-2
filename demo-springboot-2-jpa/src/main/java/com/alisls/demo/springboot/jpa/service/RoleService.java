package com.alisls.demo.springboot.jpa.service;

import com.alisls.demo.springboot.jpa.dto.role.RoleDTO;

/**
 * 角色Service
 *
 * @author Ke Wang
 */
public interface RoleService {

    RoleDTO getById(Long id);

    RoleDTO save(RoleDTO roleDTO);

    RoleDTO update(RoleDTO roleDTO);
    
}
