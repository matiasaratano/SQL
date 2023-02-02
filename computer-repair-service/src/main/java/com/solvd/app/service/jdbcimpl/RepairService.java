package com.solvd.app.service.jdbcimpl;

import com.solvd.app.dao.mysql.*;
import com.solvd.app.models.Employee;
import com.solvd.app.models.Repair;
import com.solvd.app.models.Service;
import com.solvd.app.service.IRepairService;

import java.sql.SQLException;
import java.util.ArrayList;

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
        if (newRepair.getRepairEmployees().size() != 0) {
            ArrayList<Employee> employees = newRepair.getRepairEmployees();
            for (Employee e : employees) {
                if (e.getEmployeeId() == 0)
                    employeeDAO.createEntity(e);
            }
        }
        if (newRepair.getRepairServices().size() != 0) {
            ArrayList<Service> services = newRepair.getRepairServices();
            for (Service s : services) {
                if (s.getServiceId() == 0)
                    serviceDAO.createEntity(s);
            }
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
        if (newRepair.getRepairEmployees().size() != 0) {
            ArrayList<Employee> employees = newRepair.getRepairEmployees();
            for (Employee e : employees) {
                if (e.getEmployeeId() == 0)
                    employeeDAO.createEntity(e);
            }
        }
        if (newRepair.getRepairServices().size() != 0) {
            ArrayList<Service> services = newRepair.getRepairServices();
            for (Service s : services) {
                if (s.getServiceId() == 0)
                    serviceDAO.createEntity(s);
            }
        }
        if (newRepair.getRepairDevice() != null && newRepair.getRepairDevice().getDeviceId() == 0) {
            deviceDAO.createEntity(newRepair.getRepairDevice());
        }
        repairDAO.updateEntity(newRepair);
        return newRepair;
    }
}
