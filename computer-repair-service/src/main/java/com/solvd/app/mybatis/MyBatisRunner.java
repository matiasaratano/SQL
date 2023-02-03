package com.solvd.app.mybatis;

import com.solvd.app.models.Customer;
import com.solvd.app.service.mybatisimpl.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MyBatisRunner {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisRunner.class);

    public static void main(String[] args) throws SQLException {
        CustomerService customerService = new CustomerService();
        //LOGGER.info(customerService.getEntityById(3));
        Customer c = new Customer("123", "123", "123", "123");
        LOGGER.info(customerService.createEntity(c));
        //LOGGER.info(customerService.findAll());
        //customerService.removeById(23);
    }
}