package com.solvd.app;

import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        LOGGER.info("Starting program.");
        //CustomerDAO customerDAO = new CustomerDAO();
        //ServiceDAO serviceDAO = new ServiceDAO();
        //DeviceDAO deviceDAO = new DeviceDAO();
        //EmployeeDAO employeeDAO = new EmployeeDAO();
        //RepairService repairService = new RepairService();
        try {
            //Create test
            //Customer customer = new Customer("Mark", "T", "123", "123");
            //Employee employee = new Employee("Mark", "T", "123", "123", "asd", "2021", 100);
            //Service service = new Service("arreglo", 123);
            //Device device = new Device("cel", "samsung");

            //LOGGER.info("Creating customer..");
            //customerDAO.createEntity(customer);

            //Find all test OK
            //LOGGER.info("Customers found:\n" + customerDAO.findAll());
            //LOGGER.info("Employees found:\n" + employeeDAO.findAll());
            //LOGGER.info("Services found:\n" + serviceDAO.findAll());
            //LOGGER.info("Devices found:\n" + deviceDAO.findAll());

            //Update test
            //Customer customer2 = new Customer(1, "Marksss", "T", "123", "234");
            //customerDAO.updateEntity(customer2);
            //Remove test
            //customerDAO.removeById(27);

            //RepairService rs = new RepairService();
            //Repair repair2 = rs.getRepairById(1);
            //LOGGER.info(repair2);
            //rs.createRepair(repair);
            //Repair repair = new Repair(customerDAO.getCustomersByRepairId(1), employeeDAO.getEmployeesByRepairId(1), serviceDAO.getServicesByRepairId(1), deviceDAO.getDevicesByRepairId(1), "2021");
            //repairService.createRepair(repair);


            ConnectionPool.getInstance().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}