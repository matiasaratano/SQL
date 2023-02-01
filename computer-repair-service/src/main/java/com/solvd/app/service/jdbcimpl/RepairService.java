package com.solvd.app.service.jdbcimpl;

import com.solvd.app.dao.mysql.*;
import com.solvd.app.models.Repair;
import com.solvd.app.service.IRepairService;

import java.sql.SQLException;

public class RepairService implements IRepairService {

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


    @Override
    public Repair getRepairById(int repairId) throws SQLException {
        Repair repair = repairDAO.getEntityById(repairId);
        repair.setCustomer(customerDAO.getCustomerByRepairId(repairId));
        repair.setEmployees(employeeDAO.getEmployeesByRepairId(repairId));
        repair.setServices(serviceDAO.getServicesByRepairId(repairId));
        repair.setDevice(deviceDAO.getDeviceByRepairId(repairId));
        return repair;
    }

    @Override
    public Repair createRepair(Repair newRepair) throws SQLException {
        if (newRepair.getRepairCustomer() != null && newRepair.getRepairCustomer().getCustomerId() == 0) {
            customerDAO.createEntity(newRepair.getRepairCustomer());
        }
        if (newRepair.getRepairEmployees() != null && newRepair.getRepairEmployees().get(0).getEmployeeId() == 0) {
            employeeDAO.createEntity(newRepair.getRepairEmployees().get(0));
        }
        if (newRepair.getRepairServices() != null && newRepair.getRepairServices().get(0).getServiceId() == 0) {
            serviceDAO.createEntity(newRepair.getRepairServices().get(0));
        }
        if (newRepair.getRepairDevice() != null && newRepair.getRepairDevice().getDeviceId() == 0) {
            deviceDAO.createEntity(newRepair.getRepairDevice());
        }

        repairDAO.createEntity(newRepair);
        return newRepair;
    }

    @Override
    public Repair updateRepair(Repair newRepair) throws SQLException {
        if (newRepair.getRepairCustomer() != null && newRepair.getRepairCustomer().getCustomerId() == 0) {
            customerDAO.createEntity(newRepair.getRepairCustomer());
        }
        if (newRepair.getRepairEmployees() != null && newRepair.getRepairEmployees().get(0).getEmployeeId() == 0) {
            employeeDAO.createEntity(newRepair.getRepairEmployees().get(0));
        }
        if (newRepair.getRepairServices() != null && newRepair.getRepairServices().get(0).getServiceId() == 0) {
            serviceDAO.createEntity(newRepair.getRepairServices().get(0));
        }
        if (newRepair.getRepairDevice() != null && newRepair.getRepairDevice().getDeviceId() == 0) {
            deviceDAO.createEntity(newRepair.getRepairDevice());
        }
        repairDAO.updateEntity(newRepair);
        return newRepair;
    }
}
