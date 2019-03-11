package com.huankong.fictionalfiction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FictionalfictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FictionalfictionApplication.class, args);
    }

}
