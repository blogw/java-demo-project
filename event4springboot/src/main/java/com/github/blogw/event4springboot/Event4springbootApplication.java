package com.github.blogw.event4springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Event4springbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Event4springbootApplication.class, args);
    }

}
