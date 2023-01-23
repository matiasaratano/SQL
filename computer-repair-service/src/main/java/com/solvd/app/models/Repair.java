package com.solvd.app.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "repair")
public class Repair {
    @XmlElement(name = "repairId")
    private int id;
    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    private List<Customer> customers;
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;
    @XmlElementWrapper(name = "services")
    @XmlElement(name = "service")
    private List<Service> services;
    @XmlElementWrapper(name = "devices")
    @XmlElement(name = "device")
    private List<Device> devices;
    @XmlElement
    private String repairDate;

    public Repair() {
    }

    public Repair(ArrayList<Customer> customers, ArrayList<Employee> employees, ArrayList<Service> services, ArrayList<Device> devices, String repairDate) {
        this.repairDate = repairDate;
        this.customers = customers;
        this.employees = employees;
        this.services = services;
        this.devices = devices;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<Device> getDevices() {
        return devices;
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
