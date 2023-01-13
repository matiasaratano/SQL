
DROP SCHEMA IF EXISTS `RepairService` ;

CREATE SCHEMA IF NOT EXISTS `RepairService` DEFAULT CHARACTER SET utf8 ;
USE `RepairService` ;


CREATE TABLE IF NOT EXISTS `RepairService`.`Employees` (
  `EmployeeID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NULL,
  `LastName` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  PRIMARY KEY (`EmployeeID`))


CREATE TABLE IF NOT EXISTS `RepairService`.`Customers` (
  `CustomerID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(45) NULL,
  `LastName` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  `Phone` VARCHAR(45) NULL,
  PRIMARY KEY (`CustomerID`))

CREATE TABLE IF NOT EXISTS `RepairService`.`Services` (
  `ServiceID` INT NOT NULL AUTO_INCREMENT,
  `ServiceType` VARCHAR(45) NULL,
  `ServicePrice` INT NULL,
  PRIMARY KEY (`ServiceID`))



CREATE TABLE IF NOT EXISTS `RepairService`.`Devices` (
  `DeviceID` INT NOT NULL AUTO_INCREMENT,
  `DeviceType` VARCHAR(45) NULL,
  `Brand` VARCHAR(45) NULL,
  PRIMARY KEY (`DeviceID`))


CREATE TABLE IF NOT EXISTS `RepairService`.`Repairs` (
  `RepairID` INT NOT NULL AUTO_INCREMENT,
  `RepairDate` VARCHAR(45) NULL,
  `CustomerID` INT NOT NULL,
  `EmployeeID` INT NOT NULL,
  `ServiceID` INT NOT NULL,
  `DeviceID` INT NOT NULL,
  PRIMARY KEY (`RepairID`),
  CONSTRAINT `fk_Repairs_Customers1`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `RepairService`.`Customers` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Repairs_Employees1`
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `RepairService`.`Employees` (`EmployeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Repairs_Services1`
    FOREIGN KEY (`ServiceID`)
    REFERENCES `RepairService`.`Services` (`ServiceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Repairs_Devices1`
    FOREIGN KEY (`DeviceID`)
    REFERENCES `RepairService`.`Devices` (`DeviceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)

CREATE TABLE IF NOT EXISTS `RepairService`.`Repair_Details` (
  `RepairDetailID` INT NOT NULL AUTO_INCREMENT,
  `PartUsed` VARCHAR(45) NULL,
  `Cost` INT NULL,
  `RepairID` INT NOT NULL,
  PRIMARY KEY (`RepairDetailID`),

  CONSTRAINT `fk_Repair_Details_Repairs1`
    FOREIGN KEY (`RepairID`)
    REFERENCES `RepairService`.`Repairs` (`RepairID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)



CREATE TABLE IF NOT EXISTS `RepairService`.`Invoices` (
  `InvoiceID` INT NOT NULL AUTO_INCREMENT,
  `InvoiceDate` VARCHAR(45) NULL,
  `CustomerID` INT NOT NULL,
  `EmployeeID` INT NOT NULL,
  PRIMARY KEY (`InvoiceID`),

  CONSTRAINT `fk_Invoices_Customers`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `RepairService`.`Customers` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Invoices_Employees1`
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `RepairService`.`Employees` (`EmployeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


CREATE TABLE IF NOT EXISTS `RepairService`.`Invoice_Items` (
  `InvoiceItemID` INT NOT NULL AUTO_INCREMENT,
  `Cost` INT NULL,
  `InvoiceID` INT NOT NULL,
  `RepairID` INT NOT NULL,
  PRIMARY KEY (`InvoiceItemID`),
  CONSTRAINT `fk_Invoice_Items_Invoices1`
    FOREIGN KEY (`InvoiceID`)
    REFERENCES `RepairService`.`Invoices` (`InvoiceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Invoice_Items_Repairs1`
    FOREIGN KEY (`RepairID`)
    REFERENCES `RepairService`.`Repairs` (`RepairID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


CREATE TABLE IF NOT EXISTS `RepairService`.`Payment` (
  `PaymentID` INT NOT NULL AUTO_INCREMENT,
  `PaymentDate` VARCHAR(45) NULL,
  `Amount` INT NULL,
  `CustomerID` INT NOT NULL,
  `EmployeeID` INT NOT NULL,
  PRIMARY KEY (`PaymentID`),
  CONSTRAINT `fk_Payment_Customers1`
    FOREIGN KEY (`CustomerID`)
    REFERENCES `RepairService`.`Customers` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Payment_Employees1`
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `RepairService`.`Employees` (`EmployeeID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


CREATE TABLE IF NOT EXISTS `RepairService`.`Payment_Method` (
  `PaymentMethodID` INT NOT NULL AUTO_INCREMENT,
  `PaymentMethodName` VARCHAR(45) NULL,
  PRIMARY KEY (`PaymentMethodID`))


CREATE TABLE IF NOT EXISTS `RepairService`.`Vendor` (
  `VendorID` INT NOT NULL AUTO_INCREMENT,
  `VendorName` VARCHAR(45) NULL,
  `ContactName` VARCHAR(45) NULL,
  `ContactPhone` VARCHAR(45) NULL,
  `Address` VARCHAR(45) NULL,
  PRIMARY KEY (`VendorID`))



CREATE TABLE IF NOT EXISTS `RepairService`.`Vendor_Items` (
  `VendorItemID` INT NOT NULL AUTO_INCREMENT,
  `ItemName` VARCHAR(45) NULL,
  `Cost` VARCHAR(45) NULL,
  `VendorID` INT NOT NULL,
  PRIMARY KEY (`VendorItemID`),

  CONSTRAINT `fk_Vendor_Items_Vendor1`
    FOREIGN KEY (`VendorID`)
    REFERENCES `RepairService`.`Vendor` (`VendorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)


