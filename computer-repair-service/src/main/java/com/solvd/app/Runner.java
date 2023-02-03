package com.solvd.app;

import com.solvd.app.dao.mysql.CustomerDAO;
import com.solvd.app.dao.mysql.EmployeeDAO;
import com.solvd.app.dao.mysql.ServiceDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.models.Device;
import com.solvd.app.models.Employee;
import com.solvd.app.models.Repair;
import com.solvd.app.service.jdbcimpl.RepairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {

        Repair r = new Repair("2021");
        r.setCustomer(new CustomerDAO().getEntityById(3));
        r.setEmployee(new EmployeeDAO().getEntityById(2));
        //new employee
        r.setEmployee(new Employee("Matias", "Pepe", "av 123", "33333", "Dev", "2021", 123));
        //new device
        r.setDevice(new Device("test", "test"));
        r.setService(new ServiceDAO().getEntityById(2));
        //new repair
        new RepairService().createRepair(r);
        //get by id
        LOGGER.info(new RepairService().getRepairById(33));
        //update data
        r.setId(7);
        r.setCustomer(new Customer("Nicolas", "Pepe", "Fake 1234", "999999"));
        new RepairService().updateRepair(r);

    }

}