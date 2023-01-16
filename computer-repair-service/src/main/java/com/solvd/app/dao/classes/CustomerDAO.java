package com.solvd.app.dao.classes;

import com.solvd.app.dao.interfaces.ICustomerDAO;
import com.solvd.app.classes.Customer;
import com.solvd.app.dao.MySQLDAO;

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
