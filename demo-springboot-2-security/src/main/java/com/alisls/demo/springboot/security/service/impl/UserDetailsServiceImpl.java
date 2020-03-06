package com.alisls.demo.springboot.security.service.impl;

import com.alisls.demo.springboot.security.dto.UserDetailDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户详细信息服务实现类
 *
 * @author Ke Wang
 */
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 查询用户信息
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*UserDetailDTO userDetails = new UserDetailDTO();
        userDetails.setId(1L);
        userDetails.setUsername("soosky");
        userDetails.setPassword("soosky");
        userDetails.setCredentialsNonExpired(false);
        userDetails.setAccountNonExpired(false);
        userDetails.setAccountNonLocked(false);*/

        UserDetails userDetails = User.withUsername("soosky")
            .password("$2a$10$vvWz7JKiVH8ZhC6bBX6AMu1gKATZAylg3iCNgJxsSEC1qM/./LyHq")
            .authorities("p2")
            .build();

        return userDetails;
    }

}
