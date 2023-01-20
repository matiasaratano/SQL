package com.solvd.app.dao;

import com.solvd.app.models.Device;

public interface IDeviceDAO extends IBaseDAO<Device> {
    Device getDeviceByRepairId(int repairId);
}
