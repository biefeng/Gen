package com.example.gateway.shiro.realm;

import com.example.gateway.model.UserPO;
import com.example.gateway.service.CommService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class CommRealm extends AuthorizingRealm {

    @Autowired
    private CommService commService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {


        AuthorizationInfo info = new SimpleAuthorizationInfo();

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        AuthenticationInfo info = null;

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        UserPO user = commService.getUser(username);
        if (null != user) {
            String salt = user.getPasswordSalt();
            info = new SimpleAuthenticationInfo(token, user.getPassword(), ByteSource.Util.bytes(Base64.decode(salt)), getName());
        }
        return info;

    }
}
