package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.dao.mysql.MySQLDAO;
import com.solvd.app.models.Repair;

import java.util.List;

public class RepairDAO extends MySQLDAO implements IRepairDAO {
    @Override
    public List<Repair> findAll() {
        return null;
    }

    @Override
    public Repair getRepairByCustomerId(long id) {
        return null;
    }

    @Override
    public Repair getEntityById(int id) {
        return null;
    }

    @Override
    public Repair createEntity(Repair entity) {
        return null;
    }

    @Override
    public void updateEntity(Repair entity) {

    }

    @Override
    public void delete(Repair entity) {

    }

    @Override
    public void removeById(long id) {

    }
}
