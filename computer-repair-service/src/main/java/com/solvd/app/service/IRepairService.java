package com.solvd.app.service;

import com.solvd.app.models.Repair;

import java.sql.SQLException;

public interface IRepairService {
    Repair getRepairById(int repairId) throws SQLException;

    Repair createRepair(Repair newRepair) throws SQLException;

    Repair updateRepair(Repair newRepair) throws SQLException;
}
