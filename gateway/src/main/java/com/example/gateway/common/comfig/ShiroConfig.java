package com.example.gateway.common.comfig;

import com.example.gateway.shiro.filter.LoginFilter;
import com.example.gateway.shiro.realm.CommRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager manager, LoginFilter loginFilter) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);

        Map<String, Filter>  filterMap = new HashMap<>();
        filterMap.put("login",loginFilter);
        bean.setFilters(filterMap);

        Map<String, String> filterMappings = new HashMap<>();
       // filterMappings.put("/**", "login");
        bean.setFilterChainDefinitionMap(filterMappings);

        return bean;
    }

    @Bean
    public LoginFilter loginFilter() {
        LoginFilter filter = new LoginFilter();
        return filter;
    }

    @Bean
    public Cookie cookie() {
        Cookie cookie = new SimpleCookie("user_cookie");
        cookie.setSecure(true);
        cookie.setMaxAge(3 + 3600);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager(Cookie cookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(cookie);
        String password = "2018101820181018";
        cookieRememberMeManager.setCipherKey(Base64.encode(password.getBytes()));
        return cookieRememberMeManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-256");
        matcher.setHashIterations(1024);
        matcher.setStoredCredentialsHexEncoded(false);
        return matcher;
    }

    @Bean
    public CommRealm commRealm(HashedCredentialsMatcher matcher) {
        CommRealm realm = new CommRealm();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    @Bean
    public SecurityManager securityManager(CookieRememberMeManager cookieRememberMeManager, CommRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(cookieRememberMeManager);
        securityManager.setRealm(realm);
        return securityManager;
    }


}
