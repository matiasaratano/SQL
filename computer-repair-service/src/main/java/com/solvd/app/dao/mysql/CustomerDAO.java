package com.solvd.app.dao.mysql;

import com.solvd.app.dao.ICustomerDAO;
import com.solvd.app.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO extends MySQLDAO implements ICustomerDAO {
    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
    private final static String GET_CUSTOMER = "Select * from RepairService.Customers where CustomerID=?";
    private final static String GET_ALL_CUSTOMERS = "SELECT * FROM RepairService.Customers";
    private final static String CREATE_CUSTOMER = "INSERT INTO Customers (FirstName, LastName, Address, Phone) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_CUSTOMER = "UPDATE RepairService.Customers SET FirstName = ?, LastName=?, Address = ?, Phone=? WHERE customerID= ?";
    private final static String DELETE_CUSTOMER = "DELETE FROM RepairService.Customers WHERE customerId= ?";
    private final static String CUSTOMER_BY_REPAIR = "SELECT Customers.* FROM Customers INNER JOIN Repairs ON Customers.CustomerID = Repairs.CustomerID WHERE Repairs.RepairID = ?";


    public CustomerDAO() throws SQLException {
    }

    @Override
    public Customer getEntityById(int id) throws SQLException {
        Customer customer = new Customer();
        try (Connection connection = MySQLDAO.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_CUSTOMER)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer.setId(rs.getInt("CustomerID"));
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setAddress(rs.getString("Address"));
                customer.setPhone(rs.getString("Phone"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public void createEntity(Customer entity) throws SQLException {
        try (Connection connection = MySQLDAO.getConnection(); PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, entity.getCustomerFirstName());
            statement.setString(2, entity.getCustomerLastName());
            statement.setString(3, entity.getCustomerAddress());
            statement.setString(4, entity.getCustomerPhone());
            statement.executeUpdate();
            LOGGER.info("Customer created.");
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            LOGGER.info(entity);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void updateEntity(Customer entity) {
        LOGGER.info("Updating customer with id " + entity.getCustomerId() + ".");
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);

            statement.setString(1, entity.getCustomerFirstName());
            statement.setString(2, entity.getCustomerLastName());
            statement.setString(3, entity.getCustomerAddress());
            statement.setString(4, entity.getCustomerPhone());
            statement.setInt(5, entity.getCustomerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting customer with id " + id + ".");
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Customer> findAll() {
        LOGGER.info("Finding all Customers.");
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_CUSTOMERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                customers.add(new Customer(
                        resultSet.getInt("customerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Address"),
                        resultSet.getString("Phone")
                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return customers;

    }

    @Override
    public ArrayList<Customer> getCustomersByRepairId(int repairId) {
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(
                    CUSTOMER_BY_REPAIR);
            statement.setInt(1, repairId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("CustomerID"));
                customer.setFirstName(resultSet.getString("FirstName"));
                customer.setLastName(resultSet.getString("LastName"));
                customer.setAddress(resultSet.getString("Address"));
                customer.setPhone(resultSet.getString("Phone"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerByRepairId(int repairId) {
        Customer customer = new Customer();
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(
                    CUSTOMER_BY_REPAIR);
            statement.setInt(1, repairId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt("CustomerID"));
                customer.setFirstName(resultSet.getString("FirstName"));
                customer.setLastName(resultSet.getString("LastName"));
                customer.setAddress(resultSet.getString("Address"));
                customer.setPhone(resultSet.getString("Phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
