package com.solvd.app.models;

public class InventoryItem {
    private int id;
    private Repair repair;
    private Vendor vendor;
    private double cost;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        this.repair = repair;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "id=" + id +
                ", repair=" + repair +
                ", vendor=" + vendor +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
