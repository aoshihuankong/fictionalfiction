package com.huankong.fictionalfiction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.huankong.fictionalfiction.mapper")
@EnableScheduling
@SpringBootApplication
public class FictionalfictionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FictionalfictionApplication.class, args);
    }
}
