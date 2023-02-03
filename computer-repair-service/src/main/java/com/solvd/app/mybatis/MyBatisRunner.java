package com.solvd.app.mybatis;

import com.solvd.app.models.Customer;
import com.solvd.app.models.Device;
import com.solvd.app.service.mybatisimpl.dao.MyBatisCustomerDAO;
import com.solvd.app.service.mybatisimpl.dao.MyBatisDeviceDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MyBatisRunner {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisRunner.class);

    public static void main(String[] args) throws SQLException {
        MyBatisCustomerDAO customerDAO = new MyBatisCustomerDAO();
        MyBatisDeviceDAO deviceDAO = new MyBatisDeviceDAO();
        LOGGER.info(customerDAO.getEntityById(3));
        Customer c = new Customer("123", "123", "123", "123");
        Device d = new Device("test", "test");
        deviceDAO.createEntity(d);
        customerDAO.createEntity(c);
        LOGGER.info(customerDAO.findAll());
        customerDAO.removeById(23);
    }
}