package com.solvd.app.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool implements AutoCloseable {
    private final static BasicDataSource dataSource = new BasicDataSource();
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool;


    private ConnectionPool() {
        LOGGER.info("Reading properties file.");
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("./src/main/resources/db.properties"))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("Creating connection pool to MySQL database");
        dataSource.setDriverClassName(properties.getProperty("db.driver"));

        //Local
        //dataSource.setUrl(properties.getProperty("url"));
        //dataSource.setUsername(properties.getProperty("username"));
        //dataSource.setPassword(properties.getProperty("password"));

        //Remote
        dataSource.setUrl(properties.getProperty("db.url2"));
        dataSource.setUsername(properties.getProperty("db.username2"));
        dataSource.setPassword(properties.getProperty("db.password2"));

        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);

    }

    public static ConnectionPool getInstance() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void close() throws SQLException {
        dataSource.close();
    }
}
