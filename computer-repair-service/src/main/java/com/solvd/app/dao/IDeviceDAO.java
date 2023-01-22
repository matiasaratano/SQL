package com.solvd.app.dao;

import com.solvd.app.models.Device;

import java.util.ArrayList;
import java.util.List;

public interface IDeviceDAO extends IBaseDAO<Device> {
    ArrayList<Device> getDevicesByRepairId(int repairId);

    List<Device> findAll();
}
