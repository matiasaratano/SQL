package com.solvd.app.models;

public class RepairDetail {
    private int id;
    private Repair repair;
    private String partUsed;
    private double cost;
    private String description;

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

    public String getPartUsed() {
        return partUsed;
    }

    public void setPartUsed(String partUsed) {
        this.partUsed = partUsed;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RepairDetail{" +
                "id=" + id +
                ", repair=" + repair +
                ", partUsed='" + partUsed + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }
}
