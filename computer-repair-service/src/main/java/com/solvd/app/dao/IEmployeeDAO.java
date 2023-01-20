package com.solvd.app.dao;

import com.solvd.app.models.Employee;

import java.util.List;

public interface IEmployeeDAO extends IBaseDAO<Employee> {
    List<Employee> findAll();

    Employee getEmployeeByLastName(String lastName);

    Employee getEmployeeByRepairId(int repairId);
}
