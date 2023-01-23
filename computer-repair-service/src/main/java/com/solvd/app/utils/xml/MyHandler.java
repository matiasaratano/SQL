package com.solvd.app.utils.xml;

import com.solvd.app.models.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {
    private boolean inCustomer;
    private boolean inRepair;
    private boolean inService;
    private boolean inDevice;
    private boolean inEmployee;
    private String currentElement;
    private List<Customer> customers;

    private List<Device> devices;

    private List<Employee> employees;

    private List<Repair> repairs;

    private List<Service> services;


    public MyHandler() {
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        devices = new ArrayList<>();
        repairs = new ArrayList<>();
        services = new ArrayList<>();
    }


    //Tengo que hacer un case para cada tag? y ponerle el current element y desp el metodo characters ch.toString actualiza los valores?
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        switch (qName) {
            case "customer":
                inCustomer = true;
                int id = Integer.parseInt(attributes.getValue("id"));
                String firstName = attributes.getValue("firstName");
                String lastName = attributes.getValue("lastName");
                String address = attributes.getValue("address");
                String phone = attributes.getValue("phone");

                //create a new customer object and add it to the list
                Customer customer = new Customer(id, firstName, lastName, address, phone);
                customers.add(customer);
                break;
            case "repair":
                inRepair = true;
                int rId = Integer.parseInt(attributes.getValue("id"));
                int customerId = Integer.parseInt(attributes.getValue("customerId"));
                int employeeId = Integer.parseInt(attributes.getValue("employeeId"));
                int serviceId = Integer.parseInt(attributes.getValue("serviceId"));
                int deviceId = Integer.parseInt(attributes.getValue("deviceId"));
                String repairDate = attributes.getValue("repairDate");

                //how can i work if i have and object?
                //Repair repair = new Repair(rId, customerId, employeeId, serviceId, deviceId,repairDate);
                //repairs.add(repair);
                break;
            case "service":
                inService = true;
                int sId = Integer.parseInt(attributes.getValue("id"));
                String serviceType = attributes.getValue("serviceType");
                int servicePrice = Integer.parseInt(attributes.getValue("servicePrice"));

                //create a new service object and add it to the list
                Service service = new Service(sId, serviceType, servicePrice);
                services.add(service);
                break;
            case "device":
                inDevice = true;
                int dId = Integer.parseInt(attributes.getValue("id"));
                String deviceType = attributes.getValue("deviceType");
                String brand = attributes.getValue("brand");

                //create a new device object and add it to the list
                Device device = new Device(dId, deviceType, brand);
                devices.add(device);
                break;
            case "employee":
                inEmployee = true;
                int eId = Integer.parseInt(attributes.getValue("id"));
                String eFirstName = attributes.getValue("firstName");
                String eLastName = attributes.getValue("lastName");
                String eAddress = attributes.getValue("address");
                String ePhone = attributes.getValue("phone");
                String sector = attributes.getValue("sector");
                String hireDate = attributes.getValue("hireDate");
                int salary = Integer.parseInt(attributes.getValue("salary"));

                //create a new employee object and add it to the list
                Employee employee = new Employee(eId, eFirstName, eLastName, eAddress, ePhone, sector, hireDate, salary);
                employees.add(employee);
                break;
            default:
                break;

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //super.endElement(uri, localName, qName);
        currentElement = "";
        switch (qName) {
            case "customer":
                inCustomer = false;
                break;
            case "repair":
                inRepair = false;
                break;
            case "service":
                inService = false;
                break;
            case "device":
                inDevice = false;
                break;
            case "employee":
                inEmployee = false;
                break;
            default:
                break;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //super.characters(ch, start, length);
        String value = new String(ch, start, length).trim();
        if (value.length() == 0) return;
        if (inCustomer) {
            Customer customer = new Customer();
            switch (currentElement.toLowerCase()) {
                case "id":
                    customer.setId(Integer.parseInt(value));
                    break;
                case "firstname":
                    customer.setFirstName(value);
                    break;
                case "lastname":
                    customer.setLastName(value);
                    break;
                case "address":
                    customer.setAddress(value);
                    break;
                case "phone":
                    customer.setPhone(value);
                    break;
                default:
                    break;
            }
        } else if (inRepair) {
            Repair repair = new Repair();
            switch (currentElement.toLowerCase()) {
                case "id":
                    repair.setId(Integer.parseInt(value));
                    break;
                case "customerId":
                    //repair.setCustomers(value);
                    break;
                default:
                    break;
            }
        } else if (inService) {
            Service service = new Service();
            switch (currentElement.toLowerCase()) {
                case "id":
                    service.setId(Integer.parseInt(value));
                    break;
                case "serviceType":
                    service.setServiceType(value);
                    break;
                case "servicePrice":
                    service.setServicePrice(Integer.parseInt(value));
                    break;
                default:
                    break;
            }
        } else if (inDevice) {
            Device device = new Device();
            switch (currentElement.toLowerCase()) {
                case "id":
                    device.setId(Integer.parseInt(value));
                    break;
                case "deviceType":
                    device.setDeviceType(value);
                    break;
                case "brand":
                    device.setBrand(value);
                    break;
                default:
                    break;
            }
        } else if (inEmployee) {
            Employee employee = new Employee();
            switch (currentElement.toLowerCase()) {
                case "id":
                    employee.setId(Integer.parseInt(value));
                    break;
                case "firstname":
                    employee.setFirstName(value);
                    break;
                case "lastname":
                    employee.setLastName(value);
                    break;
                case "address":
                    employee.setAddress(value);
                    break;
                case "phone":
                    employee.setPhone(value);
                    break;
                case "sector":
                    employee.setSector(value);
                    break;
                case "hireDate":
                    employee.setHireDate(value);
                    break;
                case "salary":
                    employee.setSalary(Integer.parseInt(value));
                    break;
                default:
                    break;
            }
        }
    }
}

