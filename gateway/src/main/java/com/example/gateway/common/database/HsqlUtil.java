package com.example.gateway.common.database;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The util for the HSQLDB
 */
@Component
@PropertySource(value = "classpath:init.sql")
public class HsqlUtil implements InitializingBean {

    @Autowired
    private DataSource dataSource;

    /**
     * The init sql after the in-memory database created
     */
    public static final String CREEATE_TABLE_SQL = "create table t_user (\n" +
            "userName varchar(64),   \n" +
            "password varchar (256),\n" +
            "passwordSalt varchar (256)\n" +
            ");";
    private static final String INSERT_USER = "insert into t_user(username,password,passwordsalt) values('admin','NlPKohkp6hmkrsd+KHtK7NgzCODAjH/7rdx6L2tsDAY=','L5aLzm3FbiAWOWka8BAqTg==');";


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(CREEATE_TABLE_SQL);
        statement.execute(INSERT_USER);
    }
}
