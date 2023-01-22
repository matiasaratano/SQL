package com.solvd.app.utils.xml;

import com.solvd.app.models.Customer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class MyHandler extends DefaultHandler {
    private boolean inCustomer;
    private boolean inRepair;
    private boolean inService;
    private boolean inDevice;
    private boolean inEmployee;
    private String currentElement;
    private List<Customer> customers;

    public MyHandler() {
        customers = new ArrayList<Customer>();
        customer = null;
        bFirstName = false;
        bLastName = false;

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentElement = qName;
        if (qName.equalsIgnoreCase("CUSTOMERS")) {
            customers = new ArrayList<Customer>();
        } else if (qName.equalsIgnoreCase("CUSTOMER")) {
            String id = attributes.getValue("id");
            customer = new Customer();
            customer.setId(Integer.parseInt(id));
        } else if (qName.equalsIgnoreCase("FIRSTNAME")) {
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("LASTNAME")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("ADDRESS")) {
            bAddress = true;
        } else if (qName.equalsIgnoreCase("PHONE")) {
            bPhone = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        currentElement = "";
        if (qName.equalsIgnoreCase("customer")) {
            inCustomer = false;
        } else if (qName.equalsIgnoreCase("repair")) {
            inRepair = false;
        } else if (qName.equalsIgnoreCase("service")) {
            inService = false;
        } else if (qName.equalsIgnoreCase("device")) {
            inDevice = false;
        } else if (qName.equalsIgnoreCase("employee")) {
            inEmployee = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) return;

        if (inCustomer) {
            // code to handle customer element data
            if (currentElement.equalsIgnoreCase("firstName")) {
                // handle firstName data
            } else if (currentElement.equalsIgnoreCase("lastName")) {
                // handle lastName data
            }
            // and so on...
        } else if (inRepair) {
            // code to handle repair element data
        } else if (inService) {
            // code to handle service element data
        } else if (inDevice) {
            // code to handle device element data
        } else if (inEmployee) {
            // code to handle employee element data
        }
    }
}

