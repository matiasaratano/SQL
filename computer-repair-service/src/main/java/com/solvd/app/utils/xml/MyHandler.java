package com.solvd.app.utils.xml;

import com.solvd.app.models.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    private List<Repair> repairList = null;
    private List<Employee> employeeList = null;
    private Repair repair = null;
    private Employee employee = null;
    private Customer customer = null;
    private Device device = null;
    private List<Service> servicesList = null;
    private Service service = null;
    private String date = null;
    private StringBuilder data = null;

    public List<Repair> getRepairList() {
        return repairList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<Service> getServiceList() {
        return servicesList;
    }

    boolean rId = false;
    boolean rCustomer = false;
    boolean rDevice = false;
    boolean rEmployees = false;
    boolean rEmployee = false;
    boolean rServices = false;
    boolean rService = false;
    boolean rRepairDate = false;

    boolean cId = false;
    boolean cFirstName = false;
    boolean cLastName = false;
    boolean cAddress = false;
    boolean cPhone = false;

    boolean eId = false;
    boolean eFirstName = false;
    boolean eLastName = false;
    boolean eAddress = false;
    boolean ePhone = false;
    boolean eSector = false;
    boolean eSalary = false;
    boolean eHireDate = false;

    boolean sId = false;
    boolean sServiceType = false;
    boolean sServicePrice = false;

    boolean dId = false;
    boolean dDeviceType = false;
    boolean dDevicePrice = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "repair":
                repair = new Repair();
                // initialize list
                if (repairList == null)
                    repairList = new ArrayList<>();
                break;
            case "repairId":
                rId = true;
                break;
            case "customer":
                rCustomer = true;
                customer = new Customer();
                break;
            case "customerID":
                cId = true;
                break;
            case "firstName":
                cFirstName = true;
                break;
            case "lastName":
                cLastName = true;
                break;
            case "address":
                cAddress = true;
                break;
            case "phone":
                cPhone = true;
                break;
            case "employees":
                if (employeeList == null)
                    employeeList = new ArrayList<>();
                rEmployees = true;
                break;
            case "employee":
                employee = new Employee();
                rEmployee = true;
                break;
            case "employeeId":
                eId = true;
                break;
            case "eFirstName":
                eFirstName = true;
                break;
            case "eLastName":
                eLastName = true;
                break;
            case "eAddress":
                eAddress = true;
                break;
            case "ePhone":
                ePhone = true;
                break;
            case "sector":
                eSector = true;
                break;
            case "salary":
                eSalary = true;
                break;
            case "hireDate":
                eHireDate = true;
                break;
            case "services":
                if (servicesList == null)
                    servicesList = new ArrayList<>();
                rServices = true;
                break;
            case "service":
                service = new Service();
                rService = true;
                break;
            case "serviceId":
                sId = true;
                break;
            case "serviceType":
                sServiceType = true;
                break;
            case "servicePrice":
                sServicePrice = true;
                break;
            case "device":
                device = new Device();
                rDevice = true;
                break;
            case "deviceId":
                dId = true;
                break;
            case "deviceType":
                dDeviceType = true;
                break;
            case "devicePrice":
                dDevicePrice = true;
                break;
            case "repairDate":
                rRepairDate = true;
                break;
        }
        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (rId) {
            repair.setId(Integer.parseInt(data.toString()));
            rId = false;
        } else if (rCustomer) {
            rCustomer = false;
        } else if (cId) {
            customer.setId(Integer.parseInt(data.toString()));
            rId = false;
        } else if (cFirstName) {
            customer.setFirstName(data.toString());
            cFirstName = false;
        } else if (cLastName) {
            customer.setLastName(data.toString());
            cLastName = false;
        } else if (cAddress) {
            customer.setAddress(data.toString());
            cAddress = false;
        } else if (cPhone) {
            customer.setPhone(data.toString());
            cPhone = false;
        } else if (rEmployees) {

            rEmployees = false;
        } else if (rEmployee) {
            rEmployee = false;

        } else if (eId) {
            employee.setId(Integer.parseInt(data.toString()));
            eId = false;
        } else if (eFirstName) {
            employee.setFirstName(data.toString());
            eFirstName = false;
        } else if (eLastName) {
            employee.setLastName(data.toString());
            eLastName = false;
        } else if (eAddress) {
            employee.setAddress(data.toString());
            eAddress = false;
        } else if (ePhone) {
            employee.setPhone(data.toString());
            ePhone = false;
        } else if (eSector) {
            employee.setSector(data.toString());
            eSector = false;
        } else if (eSalary) {
            employee.setSalary(Integer.parseInt(data.toString()));
            eSalary = false;
        } else if (eHireDate) {
            employee.setHireDate(data.toString());
            eHireDate = false;
        } else if (rServices) {

            rServices = false;
        } else if (rService) {

            rService = false;
        } else if (sId) {
            service.setId(Integer.parseInt(data.toString()));
            sId = false;
        } else if (sServiceType) {
            service.setServiceType(data.toString());
            sServiceType = false;
        } else if (sServicePrice) {
            service.setServicePrice(Integer.parseInt(data.toString()));
            sServicePrice = false;
        } else if (rDevice) {

            rDevice = false;
        } else if (dId) {
            device.setId(Integer.parseInt(data.toString()));
            dId = false;
        } else if (dDeviceType) {
            service.setServiceType(data.toString());
            dDeviceType = false;
        } else if (dDevicePrice) {
            service.setServicePrice(Integer.parseInt(data.toString()));
            dDevicePrice = false;
        } else if (rRepairDate) {
            repair.setRepairDate(data.toString());
            rRepairDate = false;
        }


        if (qName.equalsIgnoreCase("repair")) {
            repairList.add(repair);
        } else if (qName.equalsIgnoreCase("employee")) {
            employeeList.add(employee);
        } else if (qName.equalsIgnoreCase("service")) {
            servicesList.add(service);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}

