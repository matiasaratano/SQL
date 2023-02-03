package com.solvd.app.mybatis;

import com.solvd.app.models.Customer;
import com.solvd.app.models.Device;
import com.solvd.app.service.mybatisimpl.MyBatisCustomerService;
import com.solvd.app.service.mybatisimpl.MyBatisDeviceService;
import com.solvd.app.service.mybatisimpl.MyBatisEmployeeService;
import com.solvd.app.service.mybatisimpl.MyBatisServiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MyBatisRunner {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisRunner.class);

    public static void main(String[] args) throws SQLException {
        MyBatisCustomerService customerService = new MyBatisCustomerService();
        MyBatisDeviceService deviceService = new MyBatisDeviceService();
        MyBatisEmployeeService employeeService = new MyBatisEmployeeService();
        MyBatisServiceService serviceService = new MyBatisServiceService();
        LOGGER.info(customerService.getEntityById(3));
        Customer c = new Customer("123", "123", "123", "123");
        Device d = new Device("test", "test");
        //deviceService.createEntity(d);
        //customerService.createEntity(c);
        LOGGER.info(customerService.findAll());
        customerService.removeById(23);
        LOGGER.info(employeeService.getEntityById(7));
        LOGGER.info(serviceService.getEntityById(7));
    }
}