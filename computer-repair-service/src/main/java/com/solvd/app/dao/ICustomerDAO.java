package com.solvd.app.dao;

import com.solvd.app.dao.IBaseDAO;
import com.solvd.app.models.Customer;

import java.util.List;

public interface ICustomerDAO extends IBaseDAO<Customer> {
    List<Customer> findAll();
    Customer getCustomerByLastName(String lastName);
}
