package com.alisls.demo.springboot.mybatis.plus.mapper;

import com.alisls.demo.springboot.mybatis.plus.dto.UserAddrDTO;
import com.alisls.demo.springboot.mybatis.plus.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * UserDAO
 *
 * @author Ke Wang
 * @date 2020/5/29
 */
public interface UserMapper extends BaseMapper<UserDO> {

    UserAddrDTO getUserAndAddr(String username);

    UserAddrDTO getAddrAndUser(String addrName);

}
