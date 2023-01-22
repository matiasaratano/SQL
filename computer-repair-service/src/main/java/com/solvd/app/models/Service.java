package com.solvd.app.models;

public class Service {
    private int id;
    private String serviceType;
    private int servicePrice;

    public Service() {
    }

    public Service(int serviceId, String serviceType, int servicePrice) {
        this.id = serviceId;
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
