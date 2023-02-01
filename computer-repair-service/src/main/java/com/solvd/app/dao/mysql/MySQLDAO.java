package com.solvd.app.dao.mysql;

import com.solvd.app.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySQLDAO {
    protected Connection conn;

    public MySQLDAO() throws SQLException {
    }

    public static Connection getConnection() throws SQLException {
        return ConnectionPool.getInstance().getConnection();
    }
}
