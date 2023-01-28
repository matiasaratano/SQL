package com.solvd.app.dao.mysql;

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

public abstract class MySQLDAO {
    private static BasicDataSource dataSource = null;
    protected final static Logger LOGGER = LogManager.getLogger(MySQLDAO.class);

    static {

        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("./src/main/resources/db.properties"))) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource = new BasicDataSource();
        LOGGER.info("Creating connection pool to MySQL database");
        dataSource.setDriverClassName(properties.getProperty("driver"));

        //Local
        //dataSource.setUrl(properties.getProperty("url"));
        //dataSource.setUsername(properties.getProperty("username"));
        //dataSource.setPassword(properties.getProperty("password"));

        //Remote
        dataSource.setUrl(properties.getProperty("url2"));
        dataSource.setUsername(properties.getProperty("username2"));
        dataSource.setPassword(properties.getProperty("password2"));

        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
