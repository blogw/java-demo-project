package com.github.blogw.event4springboot.service;

import com.github.blogw.event4springboot.core.MemoryData;
import com.github.blogw.event4springboot.entity.MService;
import com.github.blogw.event4springboot.event.EntityCreateEvent;
import com.github.blogw.event4springboot.mapper.MServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterService {
    private final ApplicationEventPublisher eventPublisher;
    private final JdbcTemplate jdbcTemplate;

    public List<MService> findAll() {
        String sql = "select * from m_service";
        return jdbcTemplate.query(sql, new MServiceMapper());
    }

    public MService getMServiceByClass(String serviceClass) {
        String sql = "select * from m_service where service_class = ? ";
        return jdbcTemplate.queryForObject(sql, new MServiceMapper(), serviceClass);
    }

    @Transactional
    public void addMService(MService ms) {
        Date now = new Date();
        String sql = "insert into m_service(SERVICE_CLASS,SERVICE_NAME,SERVICE_LEVEL,DISP_ORDER,IS_AAA_ORIGINAL,DELETED,SERIAL_AT, CREATED_BY, CREATED_WITH,CREATED_AT, UPDATED_BY, UPDATED_WITH, UPDATED_AT) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, ms.getServiceClass(), ms.getServiceName(), ms.getServiceLevel(), ms.getDispOrder(), 0, 0, 1, 999999, "test", now, 999999, "test", now);
        eventPublisher.publishEvent(new EntityCreateEvent(ms));
        MemoryData.stringData.set("secret");
        throw new RuntimeException("test");
    }
}
