package com.solvd.app.dao.interfaces;

import com.solvd.app.classes.Customer;

import java.util.List;

public interface ICustomerDAO extends IBaseDAO<Customer> {
    List<Customer> findAll();
    Customer getCustomerByLastName(String lastName);
}
