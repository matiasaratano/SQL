package com.solvd.app.models;

import java.util.ArrayList;
import java.util.List;

public class Repair {
    private int id;
    private List<Customer> customers;
    private List<Employee> employees;
    private List<Service> services;
    private List<Device> devices;


    private String repairDate;

    public Repair() {
    }

    public Repair(String repairDate) {
        this.repairDate = repairDate;
        this.customers = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.services = new ArrayList<>();
        this.devices = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRepairDate() {
        return repairDate;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
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
