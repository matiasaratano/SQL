package com.solvd.app.designpatterns.facade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class FacadeRunner {
    private final static Logger LOGGER = LogManager.getLogger(FacadeRunner.class);

    public static void main(String[] args) throws SQLException {
        FacadePattern facade = new FacadePattern();
        LOGGER.info(facade.getRepairByIdJDBC());
        LOGGER.info(facade.getRepairByIdMYBATIS());
    }

}
