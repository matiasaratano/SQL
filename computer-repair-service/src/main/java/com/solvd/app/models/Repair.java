package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "repair")
public class Repair {
    @JsonProperty("repairId")
    @XmlElement(name = "repairId")
    private int id;
    @JsonProperty("customers")
    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    private List<Customer> customers;
    @JsonProperty("employees")
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;
    @JsonProperty("services")
    @XmlElementWrapper(name = "services")
    @XmlElement(name = "service")
    private List<Service> services;
    @JsonProperty("devices")
    @XmlElementWrapper(name = "devices")
    @XmlElement(name = "device")
    private List<Device> devices;

    @JsonProperty("repairDate")
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

    @JsonProperty("customers")
    public List<Customer> getRepairCustomers() {
        return customers;
    }

    @JsonProperty("employees")
    public List<Employee> getRepairEmployees() {
        return employees;
    }

    @JsonProperty("services")
    public List<Service> getRepairServices() {
        return services;
    }

    @JsonProperty("devices")
    public List<Device> getRepairDevices() {
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
