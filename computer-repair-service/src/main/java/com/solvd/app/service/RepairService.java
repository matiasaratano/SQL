package com.solvd.app.service;

import com.solvd.app.dao.mysql.*;
import com.solvd.app.models.Repair;

import java.sql.SQLException;

public class RepairService {

    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private RepairDAO repairDAO = new RepairDAO();
    private ServiceDAO serviceDAO;
    private DeviceDAO deviceDAO;

    public RepairService() throws SQLException {
        this.customerDAO = new CustomerDAO();
        this.employeeDAO = new EmployeeDAO();
        this.serviceDAO = new ServiceDAO();
        this.deviceDAO = new DeviceDAO();
    }


    public Repair getRepairById(int repairId) throws SQLException {
        Repair repair = repairDAO.getEntityById(repairId);
        repair.setCustomers(customerDAO.getCustomersByRepairId(repairId));
        repair.setEmployees(employeeDAO.getEmployeesByRepairId(repairId));
        repair.setServices(serviceDAO.getServicesByRepairId(repairId));
        repair.setDevices(deviceDAO.getDevicesByRepairId(repairId));
        return repair;
    }

    public Repair createRepair(Repair newRepair) {
        Repair repair = repairDAO.createEntity(newRepair);
        repair.setCustomers(customerDAO.getCustomersByRepairId(newRepair.getRepairId()));
        repair.setEmployees(employeeDAO.getEmployeesByRepairId(newRepair.getRepairId()));
        repair.setServices(serviceDAO.getServicesByRepairId(newRepair.getRepairId()));
        repair.setDevices(deviceDAO.getDevicesByRepairId(newRepair.getRepairId()));
        return repair;
    }

    public void updateRepair(Repair newRepair) throws SQLException {
        repairDAO.updateEntity(newRepair);
        Repair repair = repairDAO.getEntityById(newRepair.getRepairId());
        repair.setCustomers(customerDAO.getCustomersByRepairId(newRepair.getRepairId()));
        repair.setEmployees(employeeDAO.getEmployeesByRepairId(newRepair.getRepairId()));
        repair.setServices(serviceDAO.getServicesByRepairId(newRepair.getRepairId()));
        repair.setDevices(deviceDAO.getDevicesByRepairId(newRepair.getRepairId()));
    }

}
