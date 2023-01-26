package com.solvd.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "device")
public class Device {
    @JsonProperty("deviceID")
    @XmlElement(name = "deviceID")
    private int id;
    @JsonProperty("deviceType")
    @XmlElement
    private String deviceType;
    @JsonProperty("brand")
    @XmlElement
    private String brand;

    public Device() {
    }

    public Device(int id) {
        this.id = id;
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

    @JsonProperty("deviceID")
    public int getDeviceId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("deviceType")
    public String getType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @JsonProperty("brand")
    public String getDeviceBrand() {
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
