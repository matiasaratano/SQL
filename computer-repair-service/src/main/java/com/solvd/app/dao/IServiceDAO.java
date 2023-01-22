package com.solvd.app.dao;

import com.solvd.app.models.Service;

import java.util.ArrayList;
import java.util.List;

public interface IServiceDAO extends IBaseDAO<Service> {

    List<Service> findAll();

    ArrayList<Service> getServicesByRepairId(int repairId);
}
