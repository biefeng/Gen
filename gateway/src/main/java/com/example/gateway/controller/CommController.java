package com.example.gateway.controller;

import com.example.gateway.common.comfig.JwtProperties;
import com.example.gateway.jwt.JavaWebTokenFactory;
import com.example.gateway.model.UserPO;
import com.example.gateway.service.CommService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/comm")
public class CommController {

    private static final String AUTH_HEADER_NAME = "Authorization";

    @Autowired
    private JavaWebTokenFactory javaWebTokenFactory;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private CommService commService;

    @GetMapping("login")
    public ResponseEntity login(UserPO user) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status = null;

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUsername(), user.getPassword(), true);

            subject.login(usernamePasswordToken);

            Claims claims = new DefaultClaims();

            claims.setIssuer(jwtProperties.getIssuer());

            javaWebTokenFactory.setSub(user.getUsername());
            javaWebTokenFactory.setClaims(claims);
            String token = javaWebTokenFactory.createToken();
            result.put("X-Authorization", token);
            status = HttpStatus.OK;
        } catch (UnknownAccountException e) {
            result.put("messgae", "用户不存在");
            status = HttpStatus.OK;
        } catch (IncorrectCredentialsException e) {
            result.put("message", "密码错误");
            status = HttpStatus.OK;
        }
        return new ResponseEntity(result, status);
    }

    @PostMapping("/registry")
    public ResponseEntity registry(UserPO user) {
        String password = user.getPassword();
        RandomNumberGenerator numberGenerator = new SecureRandomNumberGenerator();

        ByteSource salt = numberGenerator.nextBytes();
        password = new Sha256Hash(password, salt, 1024).toBase64();
        user.setPassword(password);
        user.setPasswordSalt(salt.toBase64());

        commService.registry(user);

        return null;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        UserPO list = commService.getUser(username);
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
