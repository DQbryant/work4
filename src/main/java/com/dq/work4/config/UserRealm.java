package com.dq.work4.config;


import com.dq.work4.entity.User;
import com.dq.work4.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    LoginService loginServiceImpl;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取当前用户
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        User user = loginServiceImpl.login(usernamePasswordToken.getUsername());
        if(user==null)return null;
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }
}
