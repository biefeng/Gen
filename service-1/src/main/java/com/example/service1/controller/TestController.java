package com.example.service1.controller;


import com.alibaba.fastjson.JSON;
import com.example.service1.asm.PrintCommUtil;
import com.example.service1.asm.Printer;
import com.example.service1.common.model.PageVo;
import com.example.service1.service.TestService;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.objectweb.asm.Opcodes.V1_8;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/test")
public class TestController {
    private static final Map<String, Method> methodMap = new HashMap<>();

    static {

        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.example.service1.asm.PrintCommUtil");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            String name = m.getName();
            methodMap.put(name, m);
        }
    }

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

    @PostMapping("/template/add")
    public ResponseEntity generateTemplate(String templateStr, Map<String, String> printData) throws ClassNotFoundException {
        List<Map<String, String>> units = JSON.parseObject(templateStr, List.class);
        for (Map<String, String> unit : units) {
            String type = unit.get("type");
            switch (type){
                case "text":

            }

        }
        return new ResponseEntity(null, HttpStatus.OK);

    }
}
