package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee {

    @JsonProperty("employeeID")
    @XmlElement(name = "employeeID")
    private int id;
    @JsonProperty("eFirstName")
    @XmlElement
    private String firstName;
    @JsonProperty("eLastName")
    @XmlElement
    private String lastName;
    @JsonProperty("eAddress")
    @XmlElement
    private String address;
    @JsonProperty("ePhone")
    @XmlElement
    private String phone;
    @JsonProperty("sector")
    @XmlElement
    private String sector;
    @JsonProperty("salary")
    @XmlElement
    private int salary;
    @JsonProperty("hireDate")
    @XmlElement
    //@XmlJavaTypeAdapter(DateAdapter.class)
    private String hireDate;


    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, String address, String phone, String sector, String hireDate, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.sector = sector;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public Employee(int employeeId, String firstName, String lastName, String address, String phone, String sector, String hireDate, int salary) {
        this.id = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.sector = sector;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    @JsonProperty("firstName")
    public String getEmployeeFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getEmployeeLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("address")
    public String getEmployeeAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("phone")
    public String getEmployeePhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("sector")
    public String getEmployeeSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @JsonProperty("salary")
    public int getEmployeeSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @JsonProperty("hireDate")
    public String getEmployeeHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    @JsonProperty("employeeID")
    public int getEmployeeId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", sector='" + sector + '\'' +
                ", salary=" + salary +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
