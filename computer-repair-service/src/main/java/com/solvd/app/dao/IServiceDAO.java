package com.solvd.app.dao;

import com.solvd.app.models.Service;

public interface IServiceDAO extends IBaseDAO<Service> {
    Service getServiceByRepairId(int repairId);
}
