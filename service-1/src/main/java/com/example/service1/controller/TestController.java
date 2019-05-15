package com.example.service1.controller;


import com.example.service1.common.model.PageVo;
import com.example.service1.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Enumeration;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService service;

    @GetMapping("name")
    public ResponseEntity test(HttpServletRequest request, HttpServletResponse response) {
        Object data = "Hello,this is service-1";
        ResponseEntity entity = new ResponseEntity(data, HttpStatus.OK);
        Collection<String> headerNames = response.getHeaderNames();
        for (String s : headerNames) {
            System.out.println(s + ": " + response.getHeader(s));

        }
        return entity;
    }

    @PostMapping("name")
    public ResponseEntity test() {
        Object data = "Hello,this is service-1";
        ResponseEntity entity = new ResponseEntity(data, HttpStatus.OK);

        return entity;
    }

    @GetMapping("getImageByBing")
    public ResponseEntity getImageByBing(@RequestParam String idx) {
        String url = service.getImageByBing(idx);
        return new ResponseEntity(url, HttpStatus.OK);
    }

    public ResponseEntity blogList(PageVo pageVo) {

        HttpStatus status = HttpStatus.OK;
        Object data = null;
        return new ResponseEntity(data, status);
    }
}
