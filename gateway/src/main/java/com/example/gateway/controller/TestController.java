package com.example.gateway.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    @GetMapping("name")
    public ResponseEntity test() {
        Object data = "Hello,this is gateway";
        ResponseEntity entity = new ResponseEntity(data, HttpStatus.OK);

        return entity;
    }
}
