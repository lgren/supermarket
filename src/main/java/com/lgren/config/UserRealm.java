package com.lgren.config;

import com.lgren.pojo.po.User;
import com.lgren.service.ShopService;
import com.lgren.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;


public class UserRealm extends AuthorizingRealm {
    private Logger logger =  LoggerFactory.getLogger(this.getClass());
    private User user;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        user = userService.getUserByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (shopService.getShopCountByUserId(user.getUserId()) > 0 ? true : false) {
            simpleAuthorizationInfo.addRole("seller");
        } else {
            simpleAuthorizationInfo.addRole("buyer");
            simpleAuthorizationInfo.addRole("seller");
        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        logger.info("doGetAuthenticationInfo +"  + authenticationToken.toString());
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        logger.info(userName+token.getPassword());
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if (StringUtils.isEmptyOrWhitespace(username) || StringUtils.isEmptyOrWhitespace(password) ) {
            return simpleAuthenticationInfo;
        }
        Long userId = userService.userLogin(username,password);
        if (userId != null) {
            simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,password,getName());
        }
        return simpleAuthenticationInfo;
    }

    public static void main(String[] args) {
        String source = "user";
        Object salt = "user";
        System.out.println(new SimpleHash("MD5",source,salt,1024));
    }
}
