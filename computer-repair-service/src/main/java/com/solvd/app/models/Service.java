package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "service")
public class Service {

    @JsonProperty("serviceID")
    @XmlElement(name = "serviceID")
    private int id;
    @JsonProperty("serviceType")
    @XmlElement
    private String serviceType;
    @JsonProperty("servicePrice")
    @XmlElement
    private int servicePrice;

    public Service() {
    }

    public Service(int id) {
        this.id = id;
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

    @JsonProperty("serviceID")
    public int getServiceId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("serviceType")
    public String getType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @JsonProperty("servicePrice")
    public int getPrice() {
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
