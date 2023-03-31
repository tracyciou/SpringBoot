package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ 為 annotation (註解)，在 Spring Boot 很常見
// @SpringBootApplication 需加在 class 上面
// 加上 @SpringBootApplication 後就可以運行 Spring Boot 程式
public class DemoApplication {

    public static void main(String[] args) {
        // Java 常見的入口方法 Main
        SpringApplication.run(DemoApplication.class, args);
    }

}