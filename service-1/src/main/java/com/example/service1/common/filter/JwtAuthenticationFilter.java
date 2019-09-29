package com.example.service1.common.filter;

import com.example.service1.common.config.JwtProperties;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.*;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtProperties jwtProperties;

    private static final String AUTH_HEADER_NAME = "x-authorization";

    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String authToken = req.getHeader(AUTH_HEADER_NAME);


        if (null != authToken && !StringUtils.isEmpty(authToken)) {
            String encodedSignatureKey = jwtProperties.getSignatureKey();
            authToken = authToken.substring(TOKEN_PREFIX.length());
            SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(encodedSignatureKey));
            Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            if (jws != null) {
                jws.getBody();
            }
            chain.doFilter(request, response);
        } else {
            // handler the cors issue of OPTION request.the OPTION request can also be ignored
            res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, req.getHeader(HttpHeaders.ORIGIN.toLowerCase()));
            res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, String.valueOf(true));
            res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, req.getHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS.toLowerCase()));
            res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,"DELETE");
            response.getWriter().print("the user has not been authenticated");
        }

    }

    @Override
    public void destroy() {

    }
}
