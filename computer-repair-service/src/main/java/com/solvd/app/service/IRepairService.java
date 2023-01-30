package com.solvd.app.service;

import com.solvd.app.models.*;

import java.sql.SQLException;

public interface IRepairService {
    Repair getRepairById(int repairId) throws SQLException;

    Repair createRepair(Repair newRepair, Customer customer, Employee employee, Service service, Device device) throws SQLException;

    Repair updateRepair(int id, Customer customer, Employee employee, Service service, Device device, String repairDate) throws SQLException;
}
