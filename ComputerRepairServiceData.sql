INSERT INTO Customers (FirstName, LastName, Address, Phone)
VALUES ('John', 'Doe', '123 Main St', '555-555-5555');

INSERT INTO Employees (FirstName, LastName, Address, Phone, Sector, HireDate, Salary)
VALUES ('Jane', 'Smith', '456 Park Ave', '555-555-5556', 'Sales','1-1-2000',1000);

INSERT INTO Services (ServiceType, ServicePrice)
VALUES ('Diagnosis', 50);

INSERT INTO Devices (DeviceType, Brand)
VALUES ('Laptop', 'Dell');

INSERT INTO Repairs (CustomerID, EmployeeID, ServiceID, DeviceID, RepairDate)
VALUES (1, 1, 1, 1, '1-1-2022');

INSERT INTO Repair_Details (RepairID, PartUsed, Cost, Description)
VALUES (1, 'Hard Disk', 100, 'New Hard Disk Installed');

INSERT INTO Payment_Method (PaymentMethodName)
VALUES ('Credit Card');

INSERT INTO Payment (PaymentMethodId, PaymentDate, Amount)
VALUES (1,'2022-01-03', 150);

INSERT INTO Invoices (RepairID, PaymentID, InvoiceDate, Cost)
VALUES (1, 1, '2022-01-02', 10);

INSERT INTO Vendor (VendorName, ContactName, ContactPhone, Address)
VALUES ('HP', 'Sergio', '111111', 'California');

UPDATE Customers
SET Phone = '555-555-5557'
WHERE CustomerID = 1;

UPDATE Employees
SET LastName = 'Boca'
WHERE EmployeeID = 1;

UPDATE Services
SET ServicePrice = 22
WHERE ServiceID = 1;

UPDATE Devices
SET DeviceType = 'Phone'
WHERE DeviceID = 1;

UPDATE Repairs
SET RepairDate = '01-02-2010'
WHERE RepairID = 1;

UPDATE Customers
SET LastName = 'Osvald'
WHERE CustomerID = 2;

UPDATE Repair_Details
SET Cost = 5
WHERE Repair_DetailsID = 1;

UPDATE Payment_Method
SET PaymentMethodName = 'Debit'
WHERE PaymentMethodID = 1;

UPDATE Payment
SET Amount = 5
WHERE PaymentID = 1;

UPDATE Invoices
SET Cost = 5
WHERE InvoiceID = 1;