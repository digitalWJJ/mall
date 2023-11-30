package com.ouc.mallsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ouc")
@MapperScan(value = "com.ouc.mallmbg.mapper")
public class MallSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallSecurityApplication.class, args);
    }
}
