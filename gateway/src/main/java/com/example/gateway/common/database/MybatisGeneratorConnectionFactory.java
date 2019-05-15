package com.example.gateway.common.database;

import org.hsqldb.jdbc.JDBCPool;
import org.hsqldb.server.Server;
import org.mybatis.generator.api.ConnectionFactory;
import org.mybatis.generator.config.JDBCConnectionConfiguration;

import java.sql.*;
import java.util.Properties;

/**
 * The factory for the mybatis generator
 */
public class MybatisGeneratorConnectionFactory implements ConnectionFactory {

    private String driverClass;
    private String connectionURL;
    private String userId;
    private String init_sql;

    JDBCPool jdbcPool = new JDBCPool();

    public MybatisGeneratorConnectionFactory(JDBCConnectionConfiguration config) {
        super();
        userId = config.getUserId();
        connectionURL = config.getConnectionURL();
        driverClass = config.getDriverClass();
    }

    public MybatisGeneratorConnectionFactory() {
        super();
    }


    @Override
    public Connection getConnection() throws SQLException {


        Connection connection = null;
        try {
          /*  Driver driver = (Driver) Class.forName("org.hsqldb.jdbc.JDBCDriver").newInstance();
            connection = driver.connect("jdbc:hsqldb:mem:gateway",null);*/

            jdbcPool.setURL(connectionURL);
            jdbcPool.setUser(userId);
            connection = jdbcPool.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(init_sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jdbcPool.getConnection();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.driverClass = properties.getProperty("driverClass");
        this.connectionURL = properties.getProperty("connectionURL");
        this.userId = properties.getProperty("userId");
        this.init_sql = properties.getProperty("init_sql");
    }

    public static void main(String[] args) {

    }
}
