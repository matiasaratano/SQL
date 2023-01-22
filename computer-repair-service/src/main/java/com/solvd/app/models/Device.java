package com.solvd.app.models;

public class Device {
    private int id;
    private String deviceType;
    private String brand;

    public Device() {
    }

    public Device(String deviceType, String brand) {

        this.deviceType = deviceType;
        this.brand = brand;
    }

    public Device(int deviceId, String deviceType, String brand) {
        this.id = deviceId;
        this.deviceType = deviceType;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", deviceType='" + deviceType + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
