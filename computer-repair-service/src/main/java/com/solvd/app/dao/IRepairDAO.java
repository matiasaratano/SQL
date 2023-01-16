package com.solvd.app.dao;

import com.solvd.app.dao.IBaseDAO;
import com.solvd.app.models.Repair;

import java.util.List;

public interface IRepairDAO extends IBaseDAO<Repair> {
    List<Repair> findAll();
    Repair getRepairByCustomerId(long id);
}
