package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IDeviceDAO;
import com.solvd.app.models.Device;

public class DeviceDAO extends MySQLDAO implements IDeviceDAO {
    @Override
    public Device getEntityById(int id) {
        return null;
    }

    @Override
    public Device createEntity(Device entity) {
        return null;
    }

    @Override
    public void updateEntity(Device entity) {

    }

    @Override
    public void delete(Device entity) {

    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public Device getDeviceByRepairId(int repairId) {
        return null;
    }
}
