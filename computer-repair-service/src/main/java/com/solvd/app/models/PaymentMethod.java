package com.solvd.app.models;

public class PaymentMethod {
    private int id;
    private String methodName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
