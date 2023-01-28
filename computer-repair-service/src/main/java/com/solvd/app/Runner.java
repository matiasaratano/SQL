package com.solvd.app;

import com.solvd.app.dao.mysql.CustomerDAO;
import com.solvd.app.dao.mysql.DeviceDAO;
import com.solvd.app.dao.mysql.EmployeeDAO;
import com.solvd.app.dao.mysql.ServiceDAO;
import com.solvd.app.models.*;
import com.solvd.app.service.RepairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        Repair r = new Repair("2021");
        Customer c = new CustomerDAO().getEntityById(3);
        Employee e = new EmployeeDAO().getEntityById(2);
        Service s = new ServiceDAO().getEntityById(2);
        Device d = new DeviceDAO().getEntityById(2);
        //Create new Repair
        Repair rs = new RepairService().createRepair(r, c, e, s, d);
        //Get Repair By Id
        LOGGER.info(new RepairService().getRepairById(rs.getRepairId()));
        //Update Repair
        LOGGER.info(new RepairService().updateRepair(17, c, e, s, d, "2020"));

    }
}