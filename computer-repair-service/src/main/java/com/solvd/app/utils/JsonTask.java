package com.solvd.app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solvd.app.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonTask {
    private final static Logger LOGGER = LogManager.getLogger(JsonTask.class);

    public static void main(String[] args) throws IOException {

        //Random data to test
        Customer customer = new Customer(1, "Marksss", "T", "123", "234");
        Device device = new Device(1, "mobile", "nokia");
        ArrayList<Employee> employeesList = new ArrayList<>();
        ArrayList<Service> servicesList = new ArrayList<>();
        Repair repair = new Repair(1, customer, employeesList, servicesList, device, "2021");

        //Logic
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        om.writeValue(new File("./src/main/resources/json.files/customerTest.json"), customer);
        om.writeValue(new File("./src/main/resources/json.files/repairTest.json"), repair);
        File file = new File("./src/main/resources/json.files/customerTest.json");
        File file2 = new File("./src/main/resources/json.files/repairTest.json");
        Customer customer1 = om.readValue(file, Customer.class);
        Repair repair1 = om.readValue(file2, Repair.class);
        LOGGER.info(customer1);
        LOGGER.info(repair1);

    }
}
