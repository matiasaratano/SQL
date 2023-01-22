package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IRepairDetailDAO;
import com.solvd.app.models.RepairDetail;

public class RepairDetailDAO extends MySQLDAO implements IRepairDetailDAO {
    @Override
    public RepairDetail getEntityById(int id) {
        return null;
    }

    @Override
    public RepairDetail createEntity(RepairDetail entity) {
        return null;
    }

    @Override
    public void updateEntity(RepairDetail entity) {

    }

    @Override
    public void removeById(int id) {

    }
}