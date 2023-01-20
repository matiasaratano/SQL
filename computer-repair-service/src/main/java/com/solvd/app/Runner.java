package com.solvd.app;

import com.solvd.app.dao.mysql.CustomerDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info("Starting program.");
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            //Create test
            Customer customer = new Customer("Mark", "T", "123", "123");
            LOGGER.info("Creating customer..");
            customerDAO.createEntity(customer);
            //FindAll test
            LOGGER.info("Customers found:\n" + customerDAO.findAll());
            //Update test
            Customer customer2 = new Customer(1, "Marksss", "T", "123", "234");
            customerDAO.updateEntity(customer2);

            ConnectionPool.getInstance().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}