package com.solvd.app.dao;

import com.solvd.app.models.Employee;

import java.util.ArrayList;
import java.util.List;

public interface IEmployeeDAO extends IBaseDAO<Employee> {
    List<Employee> findAll();

    ArrayList<Employee> getEmployeeByRepairId(int repairId);
}
