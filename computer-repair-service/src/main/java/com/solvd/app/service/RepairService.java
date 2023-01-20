package com.solvd.app.service;

import com.solvd.app.dao.mysql.*;
import com.solvd.app.models.Repair;

public class RepairService {

    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private RepairDAO repairDAO;
    private ServiceDAO serviceDAO;
    private DeviceDAO deviceDAO;

    public RepairService() {
    }

    public Repair getRepairById(int repairId) {
        Repair repair = repairDAO.getEntityById(repairId);
        repair.setCustomer(customerDAO.getCustomerByRepairId(repairId));
        repair.setEmployee(employeeDAO.getEmployeeByRepairId(repairId));
        repair.setService(serviceDAO.getServiceByRepairId(repairId));
        repair.setDevice(deviceDAO.getDeviceByRepairId(repairId));
        
        return repair;

    }

}
