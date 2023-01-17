package com.solvd.app.models;

public class Invoice {
    private int id;
    private Repair repair;
    private Payment payment;
    private String invoiceDate;
    private int cost;

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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", repair=" + repair +
                ", payment=" + payment +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", cost=" + cost +
                '}';
    }
}
