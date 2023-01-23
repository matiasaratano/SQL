package com.solvd.app.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer")
public class Customer {
    @XmlElement(name = "customerID")
    private int id;
    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String address;
    @XmlElement
    private String phone;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public Customer(int id, String firstName, String lastName, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public int getCustomerId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCustomerLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
