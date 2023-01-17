package com.solvd.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue;

    private final String URL = "52.59.193.212:3306";
    private final String USERNAME = "root";
    private final String PASSWORD = "devintern";
    private final int POOL_SIZE = 10;

    private ConnectionPool() {
        try {
            connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connectionQueue.offer(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void releaseConnection(Connection connection) {
        connectionQueue.offer(connection);
    }
}
