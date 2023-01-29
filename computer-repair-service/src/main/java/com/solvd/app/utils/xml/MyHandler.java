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

    private String currTag = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder("");
        switch (qName.toLowerCase()) {
            case "repair":
                repair = new Repair();
                // initialize list
                currTag = "repair";
                if (repairList == null)
                    repairList = new ArrayList<>();
                break;
            case "repairid":
                currTag = "repairid";
                break;
            case "customer":
                currTag = "customer";
                customer = new Customer();
                break;
            case "customerid":
                currTag = "customerid";
                break;
            case "firstname":
                currTag = "firstname";
                break;
            case "lastname":
                currTag = "lastname";
                break;
            case "address":
                currTag = "address";
                break;
            case "phone":
                currTag = "phone";
                break;
            case "employees":
                currTag = "employees";
                if (employeeList == null)
                    employeeList = new ArrayList<>();
                break;
            case "employee":
                currTag = "employee";
                employee = new Employee();
                break;
            case "employeeid":
                currTag = "employeeid";
                break;
            case "efirstname":
                currTag = "efirstname";
                break;
            case "elastname":
                currTag = "elastname";
                break;
            case "eaddress":
                currTag = "eaddress";
                break;
            case "ephone":
                currTag = "ephone";
                break;
            case "sector":
                currTag = "sector";
                break;
            case "salary":
                currTag = "salary";
                break;
            case "hirehate":
                currTag = "hirehate";
                break;
            case "services":
                currTag = "services";
                if (servicesList == null)
                    servicesList = new ArrayList<>();
                break;
            case "service":
                currTag = "service";
                service = new Service();
                break;
            case "serviceid":
                currTag = "serviceid";
                break;
            case "servicetype":
                currTag = "servicetype";
                break;
            case "serviceprice":
                currTag = "serviceprice";
                break;
            case "device":
                currTag = "device";
                device = new Device();
                break;
            case "deviceid":
                currTag = "deviceid";
                break;
            case "devicetype":
                currTag = "devicetype";
                break;
            case "brand":
                currTag = "brand";
                break;
            case "repairdate":
                currTag = "repairdate";
                break;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (currTag) {
            case "repair":
                break;
            case "repairid":
                repair.setId(Integer.parseInt(data.toString()));
                break;
            case "customer":
                break;
            case "customerid":
                customer.setId(Integer.parseInt(data.toString()));
                break;
            case "firstname":
                customer.setFirstName(data.toString());
                break;
            case "lastname":
                customer.setLastName(data.toString());
                break;
            case "address":
                customer.setAddress(data.toString());
                break;
            case "phone":
                customer.setPhone(data.toString());
                repair.setCustomer(customer);
                break;
            case "employees":
                ;
                break;
            case "employee":
                break;
            case "employeeid":
                employee.setId(Integer.parseInt(data.toString()));
                break;
            case "efirstname":
                employee.setFirstName(data.toString());
                break;
            case "elastname":
                employee.setLastName(data.toString());
                break;
            case "eaddress":
                employee.setAddress(data.toString());
                break;
            case "ephone":
                employee.setPhone(data.toString());
                break;
            case "sector":
                employee.setSector(data.toString());
                break;
            case "salary":
                employee.setSalary(Integer.parseInt(data.toString()));
                break;
            case "hirehate":
                employee.setHireDate(data.toString());
                break;
            case "services":
                break;
            case "service":
                break;
            case "serviceid":
                service.setId(Integer.parseInt(data.toString()));
                break;
            case "servicetype":
                service.setServiceType(data.toString());
                break;
            case "serviceprice":
                service.setServicePrice(Integer.parseInt(data.toString()));
                break;
            case "device":
                break;
            case "deviceid":
                device.setId(Integer.parseInt(data.toString()));
                break;
            case "devicetype":
                device.setDeviceType(data.toString());
                break;
            case "brand":
                device.setBrand(data.toString());
                repair.setDevice(device);
                break;
            case "repairdate":
                repair.setRepairDate(data.toString());
                break;
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
        if (data == null) {
            data = new StringBuilder();
        } else {
            data.append(ch, start, length);
        }
    }
}

