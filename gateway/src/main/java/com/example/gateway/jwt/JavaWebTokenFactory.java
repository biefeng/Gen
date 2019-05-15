package com.example.gateway.jwt;

import com.example.gateway.common.comfig.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.jsonwebtoken.Header.COMPRESSION_ALGORITHM;
import static io.jsonwebtoken.Header.TYPE;

@Component
public class JavaWebTokenFactory {

    private String sub;
    private Claims claims;

    @Autowired
    private JwtProperties jwtProperties;


    public String createToken() {

        String encodedKey = jwtProperties.getSignatureKey();
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(encodedKey.getBytes()));
        Map<String, Object> headers = new HashMap<>();


        headers.put(TYPE, Header.JWT_TYPE);
        return Jwts.builder().setHeader(headers).setSubject(sub).setClaims(claims).signWith(key).base64UrlEncodeWith(Encoders.BASE64URL).compact();
    }

    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }

    public void setJwtProperties(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String getSub() {
        return sub;
    }


    public void setSub(String sub) {
        this.sub = sub;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
