package com.solvd.app.dao;

import com.solvd.app.models.Customer;

import java.util.List;

public interface ICustomerDAO extends IBaseDAO<Customer> {
    List<Customer> findAll();

    Customer getCustomerByLastName(String lastName);

    Customer getCustomerByRepairId(int repairId);
}
