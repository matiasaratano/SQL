INSERT INTO Customers (FirstName, LastName, Address, Phone)
VALUES ('John', 'Doe', '123 Main St', '555-555-5555');

INSERT INTO Customers (FirstName, LastName, Address, Phone)
VALUES ('Mark', 'Pe', '123 Main St', '555-555-5555');

INSERT INTO Customers (FirstName, LastName, Address, Phone)
VALUES ('Peter', 'Tim', '123 Main St', '555-555-5555');

INSERT INTO Employees (FirstName, LastName, Address, Phone, Sector, HireDate, Salary)
VALUES ('Jane', 'Smith', '456 Park Ave', '555-555-5556', 'Sales','1-1-2000',1000);

INSERT INTO Services (ServiceType, ServicePrice)
VALUES ('Diagnosis', 150);

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

DELETE FROM Invoices
WHERE InvoiceID = 1;







DELETE FROM Services
WHERE ServiceID = 1;

DELETE FROM Devices
WHERE DeviceID = 1;


DELETE FROM Repair_Details
WHERE Repair_DetailsID = 1;

DELETE FROM Payment
WHERE PaymentID = 1;

DELETE FROM Vendor
WHERE VendorID = 1;

DELETE FROM Customers
WHERE CustomerID = 1;

DELETE FROM Customers
WHERE CustomerID = 2;

DELETE FROM Repairs
WHERE RepairID = 1;

DELETE FROM Employees
WHERE EmployeeID = 1;

ALTER TABLE Payment
MODIFY Amount DECIMAL(10,2);

ALTER TABLE Customers
ADD COLUMN Email VARCHAR(255);

ALTER TABLE Customers
DROP COLUMN Email;

ALTER TABLE Payment
MODIFY Amount INT;

SELECT 
  Customers.FirstName,
  Customers.LastName,
  Employees.FirstName,
  Employees.LastName,
  Services.ServiceType,
  Devices.DeviceType,
  Devices.Brand,
  Repairs.RepairDate,
  Repair_Details.PartUsed,
  Repair_Details.Cost,
  Invoices.InvoiceDate
FROM
  Customers 
  JOIN Repairs ON Customers.CustomerID = Repairs.CustomerID
  JOIN Employees ON Repairs.EmployeeID = Employees.EmployeeID
  JOIN Services ON Repairs.ServiceID = Services.ServiceID
  JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
  JOIN Repair_Details ON Repairs.RepairID = Repair_Details.RepairID
  JOIN Invoices ON Repairs.RepairID = Invoices.RepairID