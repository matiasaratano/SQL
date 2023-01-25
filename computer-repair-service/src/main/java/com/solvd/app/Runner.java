package com.solvd.app;

import com.solvd.app.service.RepairService;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        ConnectionPool.getInstance().getConnection();

        LOGGER.info(new RepairService().getRepairById(1));

        ConnectionPool.getInstance().close();
    }
}