package com.ohgiraffers.sessionlogin.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ohgiraffers.sessionlogin")
public class Chap01SessionLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap01SessionLoginApplication.class, args);
    }

}
