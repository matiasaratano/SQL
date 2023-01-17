package com.solvd.app.models;

public class Repair {
    private int id;
    private Customer customer;
    private Employee employee;
    private Service service;
    private Device device;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", customer=" + customer +
                ", employee=" + employee +
                ", service=" + service +
                ", device=" + device +
                ", repairDate='" + repairDate + '\'' +
                '}';
    }
}
