package com.solvd.app;

import com.solvd.app.models.*;
import com.solvd.app.service.RepairService;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        ConnectionPool.getInstance().getConnection();
        Repair r = new Repair("2021");
        //Create new Repair Test
        Repair rs = new RepairService().createRepair(r, new Customer(3), new Employee(2), new Service(2), new Device(2));
        //Get Repair By Id Test
        //LOGGER.info(new RepairService().getRepairById(7));
        ConnectionPool.getInstance().close();
    }
}