package com.example.gateway.shiro.filter;

import com.example.gateway.common.comfig.ShiroProperties;
import io.jsonwebtoken.Jwt;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends AccessControlFilter {

    @Autowired
    private ShiroProperties prop;

    public String createToken() {

        return null;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String method = req.getMethod();
        String requestUri = req.getRequestURI();

        if (requestUri.equals(prop.getLoginUri())) {
            return true;
        }
        if (method.equals("OPTIONS")) {
            return true;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        return false;
    }
}
