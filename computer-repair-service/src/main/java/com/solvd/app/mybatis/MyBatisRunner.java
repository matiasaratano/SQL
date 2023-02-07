package com.solvd.app.mybatis;

import com.solvd.app.models.Customer;
import com.solvd.app.models.Device;
import com.solvd.app.service.mybatisimpl.MyBatisCustomerService;
import com.solvd.app.service.mybatisimpl.MyBatisDeviceService;
import com.solvd.app.service.mybatisimpl.MyBatisRepairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MyBatisRunner {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisRunner.class);

    public static void main(String[] args) throws SQLException {
        MyBatisCustomerService customerService = new MyBatisCustomerService();
        LOGGER.info(customerService.getEntityById(3));
        Customer c = new Customer("123", "123", "123", "123");
        customerService.createEntity(c);
        LOGGER.info(customerService.findAll());
        customerService.removeById(23);

        MyBatisDeviceService deviceService = new MyBatisDeviceService();
        Device d = new Device("test", "test");
        deviceService.createEntity(d);

        MyBatisRepairService rs = new MyBatisRepairService();
        LOGGER.info(rs.getRepairById(9));
    }
}