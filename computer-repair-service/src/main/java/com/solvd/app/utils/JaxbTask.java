package com.solvd.app.utils;

import com.solvd.app.models.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class JaxbTask {
    private final static Logger LOGGER = LogManager.getLogger(JaxbTask.class);

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        //Random data to test
        Customer customer = new Customer(1, "Marksss", "T", "123", "234");
        Device device = new Device(1, "mobile", "nokia");
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<Employee> employeesList = new ArrayList<>();
        ArrayList<Device> devicesList = new ArrayList<>();
        ArrayList<Service> servicesList = new ArrayList<>();
        customerList.add(customer);
        devicesList.add(device);
        Repair repair = new Repair(customerList, employeesList, servicesList, devicesList, "2021");

        //Single Customer
        JAXBContext c = JAXBContext.newInstance(Customer.class);
        Marshaller m = c.createMarshaller();
        m.marshal(customer, new File("./src/main/resources/xml.files/customer.xml"));
        Unmarshaller um = c.createUnmarshaller();
        Customer cu = (Customer) um.unmarshal(new FileReader("./src/main/resources/xml.files/customer.xml"));
        LOGGER.info(cu);

        //Repair Object with Collections
        JAXBContext c2 = JAXBContext.newInstance(Repair.class);
        Marshaller m2 = c2.createMarshaller();
        m2.marshal(repair, new File("./src/main/resources/xml.files/repair.xml"));
        Unmarshaller um2 = c2.createUnmarshaller();
        Repair r = (Repair) um2.unmarshal(new FileReader("./src/main/resources/xml.files/repair.xml"));
        LOGGER.info(r);

    }
}
