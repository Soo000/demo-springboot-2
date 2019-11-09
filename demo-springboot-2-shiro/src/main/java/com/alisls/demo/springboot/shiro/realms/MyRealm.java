package com.alisls.demo.springboot.shiro.realms;

import com.alisls.demo.springboot.shiro.service.UserService;
import com.alisls.demo.springboot.shiro.utils.EncryUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义 Realm
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

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

        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     * @param token
     * @return
     * @throws AuthenticationException
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

}
