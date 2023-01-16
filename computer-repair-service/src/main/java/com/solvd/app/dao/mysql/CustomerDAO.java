package com.solvd.app.dao.mysql;

import com.solvd.app.dao.ICustomerDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.dao.mysql.MySQLDAO;

import java.util.List;

public class CustomerDAO extends MySQLDAO implements ICustomerDAO {


    @Override
    public Customer getEntityById(long id) {
        return null;
    }

    @Override
    public Customer createEntity(Customer entity) {
        return null;
    }

    @Override
    public void updateEntity(Customer entity) {

    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer getCustomerByLastName(String lastName) {
        return null;
    }
}
