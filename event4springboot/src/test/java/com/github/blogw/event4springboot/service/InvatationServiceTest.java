package com.github.blogw.event4springboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InvatationServiceTest {

    @Autowired
    private InvatationService service;

    @Test
    void accept() {
        service.accept("wyg");
        service.accept("zidane");
    }
}