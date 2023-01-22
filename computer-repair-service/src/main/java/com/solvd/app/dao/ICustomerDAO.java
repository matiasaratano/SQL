package com.solvd.app.dao;

import com.solvd.app.models.Customer;

import java.util.ArrayList;
import java.util.List;

public interface ICustomerDAO extends IBaseDAO<Customer> {
    List<Customer> findAll();

    ArrayList<Customer> getCustomersByRepairId(int repairId);
}
