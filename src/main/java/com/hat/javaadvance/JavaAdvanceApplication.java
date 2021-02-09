package com.hat.javaadvance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hat.javaadvance.mybatis.mapper")
public class JavaAdvanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAdvanceApplication.class, args);
    }

}
