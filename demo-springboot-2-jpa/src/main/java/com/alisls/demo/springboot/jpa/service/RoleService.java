package com.alisls.demo.springboot.jpa.service;

import com.alisls.demo.springboot.jpa.dto.RoleDTO;
import com.alisls.demo.springboot.jpa.entity.RoleDO;
import com.alisls.demo.springboot.jpa.repository.RoleRepository;
import com.alisls.demo.springboot.jpa.util.IdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
