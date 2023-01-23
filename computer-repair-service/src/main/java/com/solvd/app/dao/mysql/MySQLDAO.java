package com.solvd.app.dao.mysql;

import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MySQLDAO {
    protected Connection connection;
    protected final static Logger LOGGER = LogManager.getLogger(MySQLDAO.class);

    public MySQLDAO() throws SQLException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public int createIfNotExists(PreparedStatement insertStatement, PreparedStatement selectStatement) {
        try {
            insertStatement.executeUpdate();
            ResultSet idResultSet = selectStatement.executeQuery();

            if (idResultSet.next())
                return idResultSet.getInt("id");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.info("Could not close connection.");
        }
    }
}
