package com.solvd.app.designpatterns.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class FactoryRunner {
    private final static Logger LOGGER = LogManager.getLogger(FactoryRunner.class);

    public static void main(String[] args) throws SQLException {
        ServiceFactory serviceFactory = new ServiceFactory();
        LOGGER.info(serviceFactory.getService(ImplType.JDBC).getRepairById(9));
        LOGGER.info(serviceFactory.getService(ImplType.MYBATIS).getRepairById(33));
    }
}