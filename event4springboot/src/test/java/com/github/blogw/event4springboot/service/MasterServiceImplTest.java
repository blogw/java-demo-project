package com.github.blogw.event4springboot.service;

import com.github.blogw.event4springboot.entity.MService;
import com.github.blogw.event4springboot.service.MasterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MasterServiceImplTest {
    @Autowired
    private MasterService service;

    @Test
    void findAll() {
        List<MService> list = service.findAll();
        assertEquals(list.size(), 9);
    }

    @Test
    void getMServiceByClass() {
        MService ms = service.getMServiceByClass("chuo_aaa_portal#ev");
        assertEquals(ms.getServiceClass(), "chuo_aaa_portal#ev");
    }

    @Test
    void addMService() {
        MService ms = new MService();
        ms.setServiceClass("test");
        ms.setServiceName("test_name");
        ms.setServiceLevel("test_level");
        ms.setDispOrder(1);
        service.addMService(ms);
    }
}