package com.alisls.demo.springboot.shiro.realms;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alisls.demo.springboot.shiro.service.UserService;
import com.alisls.demo.springboot.shiro.utils.EncryUtils;

/**
 * 自定义 Realm
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 进行权限校验的时候会调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 主体名
        String username = (String) principals.getPrimaryPrincipal();
        // 根据用户名查询角色
        List<String> roles = userService.getRolesByUsername(username);
        
        // 授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        
        // 添加用户角色
        simpleAuthorizationInfo.addRoles(roles);
        //simpleAuthorizationInfo.setRoles(roles);
        
        // 添加权限信息 
        Collection<String> permissions = getPermissionsByRoles();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        //simpleAuthorizationInfo.setStringPermissions(stringPermissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证，用户登录的时候会调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        // 登录用户名
        String username = (String) token.getPrincipal();
        // 从数据库中查询密码
        String password = userService.getPasswordByUsername(username);
        String encryptedPassword = EncryUtils.getMD5(password);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, encryptedPassword, null, getName());
        return simpleAuthenticationInfo;
    }
    
    /**
     * 根据角色查询权限信息
     * @return
     */
    private Set<String> getPermissionsByRoles() {
    	return new HashSet<String>();
    }

}
