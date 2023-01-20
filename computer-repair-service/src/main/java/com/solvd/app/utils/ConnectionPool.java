package com.solvd.app.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool implements AutoCloseable {
    private static ConnectionPool connectionPool;
    private final static BasicDataSource dataSource = new BasicDataSource();
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private ConnectionPool() {
        LOGGER.info("Creating connection pool to MySQL database");
        //LOGGER.info("Reading properties file.");
        //Properties properties = new Properties();
        //try (InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/mysql.db.properties"))) {
        //properties.load(inputStream);
        // } catch (IOException e) {
        //throw new RuntimeException(e);
        // }


        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //dataSource.setUrl("52.59.193.212:3306/RepairService");
        dataSource.setUrl("jdbc:mysql://localhost:3306/RepairService");

        dataSource.setUsername("root");
        //dataSource.setPassword("devintern");
        dataSource.setPassword("root");
        dataSource.setInitialSize(5);
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