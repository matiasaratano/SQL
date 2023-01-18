SHOW TABLES;

-- 10 statements for insertion.

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

-- 10 statements for updating.

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

-- 10 statements for deletions.

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

-- 5 alter table.

ALTER TABLE Payment
MODIFY Amount DECIMAL(10,2);

ALTER TABLE Customers
ADD COLUMN Email VARCHAR(255);

ALTER TABLE Customers
DROP COLUMN Email;

ALTER TABLE Payment
MODIFY Amount INT;

-- 1 big statement to join all tables in the database.

SELECT 
    Employees.FirstName AS EmployeeFirstName, 
    Employees.LastName AS EmployeeLastName, 
    Customers.FirstName AS CustomerFirstName, 
    Customers.LastName AS CustomerLastName, 
    Services.ServiceType, 
    Devices.DeviceType, 
    Devices.Brand, 
    Repairs.RepairDate
FROM Repairs
JOIN Employees ON Repairs.EmployeeID = Employees.EmployeeID
JOIN Customers ON Repairs.CustomerID = Customers.CustomerID
JOIN Services ON Repairs.ServiceID = Services.ServiceID
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID;

-- 5 statements with left, right, inner, outer joins.

SELECT Repairs.*, Customers.*
FROM Repairs
LEFT JOIN Customers ON Repairs.CustomerID = Customers.CustomerID;

SELECT Customers.*, Repairs.*
FROM Repairs
RIGHT JOIN Customers ON Repairs.CustomerID = Customers.CustomerID;

SELECT Repairs.*, Customers.*
FROM Repairs
INNER JOIN Customers ON Repairs.CustomerID = Customers.CustomerID;

SELECT Repairs.RepairID, Repairs.RepairDate, Employees.FirstName, Employees.LastName
FROM Repairs
CROSS JOIN Employees;

-- 7 statements with aggregate functions and group by and without having.

SELECT Devices.DeviceType, COUNT(Repairs.RepairID) AS TotalRepairs
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
GROUP BY Devices.DeviceType;

SELECT Customers.FirstName, Customers.LastName, SUM(Services.ServicePrice) AS TotalCost
FROM Repairs
JOIN Customers ON Repairs.CustomerID = Customers.CustomerID
JOIN Services ON Repairs.ServiceID = Services.ServiceID
GROUP BY Customers.FirstName, Customers.LastName;

SELECT Employees.Sector, AVG(Employees.Salary) AS AverageSalary
FROM Employees
GROUP BY Employees.Sector;

SELECT Devices.DeviceType, MIN(Repairs.RepairDate) AS EarliestRepairDate
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
GROUP BY Devices.DeviceType;

SELECT Devices.DeviceType, MAX(Repairs.RepairDate) AS LatestRepairDate
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
GROUP BY Devices.DeviceType;

SELECT Devices.DeviceType, GROUP_CONCAT(DISTINCT Services.ServiceType) AS ServiceTypes
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
JOIN Services ON Repairs.ServiceID = Services.ServiceID
GROUP BY Devices.DeviceType;

SELECT Employees.FirstName, Employees.LastName, GROUP_CONCAT(Repairs.RepairDate) AS RepairsDates
FROM Repairs
JOIN Employees ON Repairs.EmployeeID = Employees.EmployeeID
GROUP BY Employees.FirstName, Employees.LastName;

-- 7 statements with aggregate functions and group by and with having.

SELECT Devices.DeviceType, COUNT(Repairs.RepairID) AS TotalRepairs
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
GROUP BY Devices.DeviceType
HAVING COUNT(Repairs.RepairID) > 10;

SELECT Customers.FirstName, Customers.LastName, SUM(Services.ServicePrice) AS TotalCost
FROM Repairs
JOIN Customers ON Repairs.CustomerID = Customers.CustomerID
JOIN Services ON Repairs.ServiceID = Services.ServiceID
GROUP BY Customers.FirstName, Customers.LastName
HAVING SUM(Services.ServicePrice) > 1000;

SELECT Employees.Sector, AVG(Employees.Salary) AS AverageSalary
FROM Employees
GROUP BY Employees.Sector
HAVING AVG(Employees.Salary) > 50000;

SELECT Devices.DeviceType, MIN(Repairs.RepairDate) AS EarliestRepairDate
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
GROUP BY Devices.DeviceType
HAVING MIN(Repairs.RepairDate) >= DATE_SUB(NOW(), INTERVAL 1 YEAR);

SELECT Devices.DeviceType, MAX(Repairs.RepairDate) AS LatestRepairDate
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
GROUP BY Devices.DeviceType
HAVING MAX(Repairs.RepairDate) >= DATE_SUB(NOW(), INTERVAL 1 MONTH);

SELECT Devices.DeviceType, GROUP_CONCAT(DISTINCT Services.ServiceType) AS ServiceTypes
FROM Repairs
JOIN Devices ON Repairs.DeviceID = Devices.DeviceID
JOIN Services ON Repairs.ServiceID = Services.ServiceID
GROUP BY Devices.DeviceType
HAVING COUNT(DISTINCT Services.ServiceType) > 2;

SELECT Employees.FirstName, Employees.LastName, GROUP_CONCAT(Repairs.RepairDate) AS RepairsDates
FROM Repairs
JOIN Employees ON Repairs.EmployeeID = Employees.EmployeeID
GROUP BY Employees.FirstName, Employees.LastName
HAVING COUNT(Repairs.RepairID) > 5;
