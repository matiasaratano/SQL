package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IServiceDAO;
import com.solvd.app.models.Service;

public class ServiceDAO extends MySQLDAO implements IServiceDAO {
    @Override
    public Service getEntityById(int id) {
        return null;
    }

    @Override
    public Service createEntity(Service entity) {
        return null;
    }

    @Override
    public void updateEntity(Service entity) {

    }

    @Override
    public void delete(Service entity) {

    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public Service getServiceByRepairId(int repairId) {
        return null;
    }
}
