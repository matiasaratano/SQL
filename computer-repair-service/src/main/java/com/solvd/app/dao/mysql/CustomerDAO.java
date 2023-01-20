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
    private final Connection connection;

    public CustomerDAO() throws SQLException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Customer getEntityById(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        Customer customer = new Customer();
        try (PreparedStatement ps = c.prepareStatement(GET_CUSTOMER)) {
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
            String sql = "INSERT INTO Customers (FirstName, LastName, Address, Phone) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getAddress());
            statement.setString(4, entity.getPhone());
            statement.executeUpdate();
            LOGGER.info("Customer created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void updateEntity(Customer entity) {

    }

    @Override
    public void delete(Customer entity) {

    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public List<Customer> findAll() {
        LOGGER.info("Finding all Persons.");
        List<Customer> customers = new ArrayList<>();
        try {
            String query = "SELECT * FROM customers";
            PreparedStatement statement = connection.prepareStatement(query);

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
    public Customer getCustomerByLastName(String lastName) {
        return null;
    }

    @Override
    public Customer getCustomerByRepairId(int repairId) {
        return null;
    }
}
