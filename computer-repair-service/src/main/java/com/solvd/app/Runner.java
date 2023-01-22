package com.solvd.app;

import com.solvd.app.dao.mysql.CustomerDAO;
import com.solvd.app.dao.mysql.DeviceDAO;
import com.solvd.app.dao.mysql.RepairDAO;
import com.solvd.app.dao.mysql.ServiceDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.service.RepairService;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info("Starting program.");
        CustomerDAO customerDAO = new CustomerDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        DeviceDAO deviceDAO = new DeviceDAO();
        RepairDAO repairDAO = new RepairDAO();
        try {
            //Create test
            Customer customer = new Customer("Mark", "T", "123", "123");
            LOGGER.info("Creating customer..");
            customerDAO.createEntity(customer);

            //Find all test
            //LOGGER.info("Customers found:\n" + customerDAO.findAll());
            //LOGGER.info("Services found:\n" + serviceDAO.findAll());
            //LOGGER.info("Devices found:\n" + deviceDAO.findAll());

            //Update test
            Customer customer2 = new Customer(1, "Marksss", "T", "123", "234");
            customerDAO.updateEntity(customer2);
            //Remove test
            customerDAO.removeById(20);

            RepairService rs = new RepairService();
            LOGGER.info(rs.getRepairById(1));

            ConnectionPool.getInstance().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}