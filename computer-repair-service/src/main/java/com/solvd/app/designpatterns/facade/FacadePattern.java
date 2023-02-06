package com.solvd.app.designpatterns.facade;

import com.solvd.app.models.Repair;
import com.solvd.app.service.jdbcimpl.RepairService;
import com.solvd.app.service.mybatisimpl.MyBatisRepairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class FacadePattern {
    private RepairService jdbcRepairService;
    private MyBatisRepairService myBatisRepairService;
    private final static Logger LOGGER = LogManager.getLogger(FacadePattern.class);

    private Scanner scanner = new Scanner(System.in);

    public FacadePattern() throws SQLException {
        jdbcRepairService = new RepairService();
        myBatisRepairService = new MyBatisRepairService();
    }

    public Repair getRepairByIdJDBC() throws SQLException {
        return jdbcRepairService.getRepairById(insertNumber());
    }

    public Repair getRepairByIdMyBatis() throws SQLException {
        return myBatisRepairService.getRepairById(insertNumber());
    }

    public int insertNumber() {
        LOGGER.info("Insert id number of the repair");
        try {
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid Input");
        }
        return 0;
    }
}
