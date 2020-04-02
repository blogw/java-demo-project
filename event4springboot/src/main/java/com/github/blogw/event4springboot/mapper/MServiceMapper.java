package com.github.blogw.event4springboot.mapper;

import com.github.blogw.event4springboot.entity.MService;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MServiceMapper implements RowMapper<MService> {
    @Override
    public MService mapRow(ResultSet resultSet, int i) throws SQLException {
        MService stu = new MService();
        stu.setServiceClass(resultSet.getString("SERVICE_CLASS"));
        stu.setServiceName(resultSet.getString("SERVICE_NAME"));
        stu.setServiceLevel(resultSet.getString("SERVICE_LEVEL"));
        stu.setDispOrder(resultSet.getInt("DISP_ORDER"));
        return stu;
    }
}
