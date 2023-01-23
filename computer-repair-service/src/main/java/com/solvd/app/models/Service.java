package com.solvd.app.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
public class Service {
    @XmlElement(name = "serviceID")
    private int id;
    @XmlElement
    private String serviceType;
    @XmlElement
    private int servicePrice;

    public Service() {
    }

    public Service(int serviceId, String serviceType, int servicePrice) {
        this.id = serviceId;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public Service(String serviceType, int servicePrice) {
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceType='" + serviceType + '\'' +
                ", servicePrice=" + servicePrice +
                '}';
    }
}
