package com.solvd.app.models;

import java.util.List;

public class Repair {
    private int id;
    private List<Customer> customers;
    private List<Employee> employees;
    private List<Service> services;
    private List<Device> devices;
    private String repairDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public void setCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void setEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void setService(Service service) {
        this.services.add(service);
    }

    public void setDevice(Device device) {
        this.devices.add(device);
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", customers=" + customers +
                ", employees=" + employees +
                ", services=" + services +
                ", devices=" + devices +
                ", repairDate='" + repairDate + '\'' +
                '}';
    }
}
