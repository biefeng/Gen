package com.example.gateway;

import com.example.gateway.common.database.HsqlUtil;
import org.hsqldb.jdbc.JDBCPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private HsqlUtil hsqlUtil;

    @Autowired
    private JDBCPool jdbcPool;

    @Test
    public void name() throws SQLException {
        Connection connection = jdbcPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_user");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }

    }
}
