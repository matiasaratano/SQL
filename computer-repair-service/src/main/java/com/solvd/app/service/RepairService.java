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
        newRepair.setCustomer(customerDAO.getEntityById(customer.getCustomerId()));
        newRepair.setEmployee(employeeDAO.getEntityById(employee.getEmployeeId()));
        newRepair.setService(serviceDAO.getEntityById(service.getServiceId()));
        newRepair.setDevice(deviceDAO.getEntityById(device.getDeviceId()));
        repairDAO.createEntity(newRepair);
        return newRepair;
    }

    public Repair updateRepair(int id, Customer customer, Employee employee, Service service, Device device, String repairDate) throws SQLException {
        Repair repair = repairDAO.getEntityById(id);
        repair.setId(id);
        repair.setCustomer(customerDAO.getEntityById(customer.getCustomerId()));
        repair.setEmployee(employeeDAO.getEntityById(employee.getEmployeeId()));
        repair.setService(serviceDAO.getEntityById(service.getServiceId()));
        repair.setDevice(deviceDAO.getEntityById(device.getDeviceId()));
        repair.setRepairDate(repairDate);
        repairDAO.updateEntity(repair);
        return repair;
    }

}
