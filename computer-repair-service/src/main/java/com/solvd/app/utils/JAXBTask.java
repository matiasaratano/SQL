package com.solvd.app.utils;

import com.solvd.app.models.Customer;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JAXBTask {
    private final static Logger LOGGER = LogManager.getLogger(JAXBTask.class);

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Customer customer = new Customer(1, "Marksss", "T", "123", "234");

        JAXBContext c = JAXBContext.newInstance(Customer.class);
        Marshaller m = c.createMarshaller();
        m.marshal(customer, new File("./src/main/resources/xmlFiles/customer.xml"));
        Unmarshaller um = c.createUnmarshaller();
        Customer cu = (Customer) um.unmarshal(new FileReader("./src/main/resources/xmlFiles/customer.xml"));
        LOGGER.info(cu);
    }
}
