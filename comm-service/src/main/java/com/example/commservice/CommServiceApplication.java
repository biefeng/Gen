package com.example.commservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.example.commservice.dao"})
public class CommServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommServiceApplication.class, args);
        String str = "\\";
    }
}



