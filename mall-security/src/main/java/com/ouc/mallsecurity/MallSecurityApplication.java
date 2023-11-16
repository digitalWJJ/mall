package com.ouc.mallsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ouc")
public class MallSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSecurityApplication.class, args);
    }

}
