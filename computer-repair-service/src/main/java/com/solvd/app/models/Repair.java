package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement(name = "repair")
public class Repair {
    @JsonProperty("repairId")
    @XmlElement(name = "repairId")
    private int id;
    @JsonProperty("customer")
    @XmlElement(name = "customer")
    private Customer customer;
    @JsonProperty("employees")
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private ArrayList<Employee> employees;
    @JsonProperty("services")
    @XmlElementWrapper(name = "services")
    @XmlElement(name = "service")
    private ArrayList<Service> services;
    @JsonProperty("device")
    @XmlElement(name = "device")
    private Device device;

    @JsonProperty("repairDate")
    @XmlElement
    private String repairDate;

    public Repair() {
        this.employees = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Repair(Customer customer, ArrayList<Employee> employees, ArrayList<Service> services, Device device, String repairDate) {
        this.repairDate = repairDate;
        this.customer = customer;
        this.employees = employees;
        this.services = services;
        this.device = device;
    }

    public Repair(int id, Customer customer, ArrayList<Employee> employees, ArrayList<Service> services, Device device, String repairDate) {
        this.repairDate = repairDate;
        this.id = id;
        this.customer = customer;
        this.employees = employees;
        this.services = services;
        this.device = device;
    }

    public Repair(String repairDate) {
        this.repairDate = repairDate;
        this.employees = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Repair(int id, String repairDate) {
        this.id = id;
        this.repairDate = repairDate;
        this.employees = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    @JsonProperty("repairId")
    public int getRepairId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("repairDate")
    public String getDate() {
        return repairDate;
    }

    @JsonProperty("customer")
    public Customer getRepairCustomer() {
        return customer;
    }

    @JsonProperty("employees")
    public ArrayList<Employee> getRepairEmployees() {
        return employees;
    }

    @JsonProperty("services")
    public ArrayList<Service> getRepairServices() {
        return services;
    }

    @JsonProperty("device")
    public Device getRepairDevice() {
        return device;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setEmployee(Employee employee) {
        this.employees.add(employee);
    }


    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public void setService(Service service) {
        this.services.add(service);
    }


    public void setDevice(Device device) {
        this.device = device;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;

    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", customer=" + customer +
                ", employees=" + employees +
                ", services=" + services +
                ", device=" + device +
                ", repairDate='" + repairDate + '\'' +
                '}';
    }
}
