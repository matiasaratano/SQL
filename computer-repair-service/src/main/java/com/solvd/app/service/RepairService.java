package com.solvd.app.service;

import com.solvd.app.dao.mysql.*;
import com.solvd.app.models.Repair;

import java.sql.SQLException;

public class RepairService {

    private CustomerDAO customerDAO = new CustomerDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private RepairDAO repairDAO = new RepairDAO();
    private ServiceDAO serviceDAO = new ServiceDAO();
    private DeviceDAO deviceDAO = new DeviceDAO();

    public RepairService() throws SQLException {
    }


    public Repair getRepairById(int repairId) throws SQLException {
        Repair repair = repairDAO.getEntityById(repairId);
        repair.setCustomers(customerDAO.getCustomersByRepairId(repairId));
        repair.setEmployees(employeeDAO.getEmployeeByRepairId(repairId));
        repair.setServices(serviceDAO.getServiceByRepairId(repairId));
        repair.setDevices(deviceDAO.getDevicesByRepairId(repairId));
        return repair;
    }


}
