package com.example.gateway.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.Assert;
import org.junit.Test;
import sun.security.x509.AlgorithmId;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JwtTest {

    @Test
    public void name() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        /*KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        keyGenerator.init(256);
        Key key = keyGenerator.generateKey();*/
        Key key  = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String encodedString = new String(Base64.getEncoder().encode(key.getEncoded()));
        System.out.println(encodedString);
        Key key1 = Keys.hmacShaKeyFor(Base64.getDecoder().decode(encodedString.getBytes()));
        System.out.println(key.getEncoded().length);
        Encoder<byte[],String> encoder = Encoders.BASE64URL;
        String token = Jwts.builder().setSubject("Joe").signWith(key).base64UrlEncodeWith(encoder).compact();
        System.out.println(token);
        assert Jwts.parser().setSigningKey(key1).parseClaimsJws(token).getBody().getSubject().equals("Joe");
    }

    @Test
    public void name1() {
        String key =  "lEKiw5VnN2Rf3rV4NZxtsrl70ogHX2piwE0Ecj+NNiwsPG9kdmqwxHBCa839g5M91jm8JDs2b4FfhF/iRTPvYQ==";
        assert Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(key.getBytes()))).parseClaimsJws("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2UifQ.qW-5Cpq6D3cdsVuAFeLiB9GtqA5Cj1qf68sTHVmW9PPhqUZ5qPn5TN1qVWh14iQt28llXhv2W00y2WjxZJHyIw").getBody().getSubject().equals("Joe");
    }

    @Test
    public void name2() {
        System.out.println(1<<2);
    }
}
