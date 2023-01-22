package com.solvd.app.dao.mysql;

import com.solvd.app.dao.ICustomerDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO extends MySQLDAO implements ICustomerDAO {
    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
    private final static String GET_CUSTOMER = "Select * from RepairService.Customers where CustomerID=?";
    private final static String GET_ALL_CUSTOMERS = "SELECT * FROM RepairService.Customers";
    private final static String CREATE_CUSTOMER = "INSERT INTO Customers (FirstName, LastName, Address, Phone) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_CUSTOMER = "UPDATE RepairService.Customers SET FirstName = ?, LastName=?, Address = ?, Phone=? WHERE customerID= ?";
    private final static String DELETE_CUSTOMER = "DELETE FROM RepairService.Customers WHERE customerId= ?";
    private final static String CUSTOMER_BY_REPAIR = "SELECT customers.* FROM customers INNER JOIN repairs ON customers.CustomerID = repairs.CustomerID WHERE repairs.RepairID = ?";
    private final Connection connection;

    public CustomerDAO() throws SQLException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Customer getEntityById(int id) throws SQLException {
        Customer customer = new Customer();
        try (PreparedStatement ps = connection.prepareStatement(GET_CUSTOMER)) {
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
        } finally {
            ConnectionPool.getInstance().close();
        }
        return customer;
    }

    @Override
    public Customer createEntity(Customer entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_CUSTOMER);

            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.executeUpdate();
            LOGGER.info("Customer created.");
            LOGGER.info(entity);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void updateEntity(Customer entity) {
        LOGGER.info("Updating customer with id " + entity.getId() + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);

            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting customer with id " + id + ".");
        try {
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
        try {
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
        try {
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

}
