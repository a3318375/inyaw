package com.inyaw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.inyaw.**.dao")
@SpringBootApplication
public class InyawApplication {

    public static void main(String[] args) {
        SpringApplication.run(InyawApplication.class, args);
    }

}
