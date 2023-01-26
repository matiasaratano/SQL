package com.solvd.app.service;

import com.solvd.app.dao.mysql.*;
import com.solvd.app.models.*;

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
        repair.setCustomer(customerDAO.getCustomerByRepairId(repairId));
        repair.setEmployees(employeeDAO.getEmployeesByRepairId(repairId));
        repair.setServices(serviceDAO.getServicesByRepairId(repairId));
        repair.setDevice(deviceDAO.getDeviceByRepairId(repairId));
        return repair;
    }

    public Repair createRepair(Repair newRepair, Customer customer, Employee employee, Service service, Device device) throws SQLException {
        Repair repair = new Repair();
        repair.setCustomer(customerDAO.getEntityById(customer.getCustomerId()));
        repair.setEmployee(employeeDAO.getEntityById(employee.getEmployeeId()));
        repair.setService(serviceDAO.getEntityById(service.getServiceId()));
        repair.setDevice(deviceDAO.getEntityById(device.getDeviceId()));
        repair.setRepairDate(newRepair.getDate());
        repairDAO.createEntity(repair);
        return repair;
    }

    public void updateRepair(Repair newRepair, Customer customer, Employee employee, Service service, Device device) throws SQLException {
        Repair repair = repairDAO.getEntityById(newRepair.getRepairId());
        repair.setCustomer(customerDAO.createEntity(customer));
        repair.setEmployee(employeeDAO.createEntity(employee));
        repair.setService(serviceDAO.createEntity(service));
        repair.setDevice(deviceDAO.createEntity(device));
        repairDAO.updateEntity(newRepair);
    }

}
