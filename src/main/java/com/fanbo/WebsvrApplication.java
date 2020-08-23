package com.fanbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WebsvrApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsvrApplication.class, args);
    }
}
