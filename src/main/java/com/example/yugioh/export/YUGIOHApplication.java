package com.example.yugioh.export;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.yugioh.export"})
public class YUGIOHApplication {
    public static void main(String[] args) {
        SpringApplication.run(YUGIOHApplication.class, args);
    }

}
