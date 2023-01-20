package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IEmployeeDAO;
import com.solvd.app.models.Employee;

import java.util.List;

public class EmployeeDAO extends MySQLDAO implements IEmployeeDAO {
    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public Employee getEmployeeByLastName(String lastName) {
        return null;
    }

    @Override
    public Employee getEmployeeByRepairId(int repairId) {
        return null;
    }

    @Override
    public Employee getEntityById(int id) {
        return null;
    }

    @Override
    public Employee createEntity(Employee entity) {
        return null;
    }

    @Override
    public void updateEntity(Employee entity) {

    }

    @Override
    public void delete(Employee entity) {

    }

    @Override
    public void removeById(long id) {

    }
}
