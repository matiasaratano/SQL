package com.solvd.app.designpatterns.factory;

import com.solvd.app.service.IRepairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class FactoryRunner {
    private final static Logger LOGGER = LogManager.getLogger(FactoryRunner.class);

    public static void main(String[] args) throws SQLException {
        ServiceFactory serviceFactory = new ServiceFactory();
        IRepairService repairService = serviceFactory.getService(ImplType.JDBC);
        if (repairService != null) {
            try {
                LOGGER.info(repairService.getRepairById(9));
            } catch (SQLException e) {
                LOGGER.error("SQL exception: ", e);
            }
        } else {
            LOGGER.error("Error getting repair service");
        }

        IRepairService myBatisRepairService = serviceFactory.getService(ImplType.MYBATIS);
        if (myBatisRepairService != null) {
            try {
                LOGGER.info(myBatisRepairService.getRepairById(9));
            } catch (SQLException e) {
                LOGGER.error("SQL exception: ", e);
            }
        } else {
            LOGGER.error("Error getting MyBatis repair service");
        }
    }
}