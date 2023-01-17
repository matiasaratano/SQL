package com.solvd.app.dao.mysql;

import com.solvd.app.utils.ConnectionPool;
import com.solvd.app.dao.ICustomerDAO;
import com.solvd.app.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.persistence.EntityNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class CustomerDAO extends MySQLDAO implements ICustomerDAO {
    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
    private final static String GET_CUSTOMER = "Select * from Customers where CustomerID=?";

    @Override
    public Customer getEntityById(int id) {
        //Connection c = ConnectionPool.getInstance().getConnection();
        Connection c = null;
        try {
            c = ConnectionPool.getInstance().getConnection();
        } catch (Exception e) {
            LOGGER.error("Error getting connection from the pool", e);
        }
        Customer customer = new Customer();
        try (PreparedStatement ps = c.prepareStatement(GET_CUSTOMER)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer.setFirstName(rs.getString("FirstName"));
                customer.setLastName(rs.getString("LastName"));
                customer.setPhone(rs.getString("Address"));
                customer.setFirstName(rs.getString("Phone"));
            }
            if (!rs.next()) {
                throw new EntityNotFoundException("The customer with id " + id + " was not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(c);
        }
        return customer;
    }

    @Override
    public Customer createEntity(Customer entity) {
        return null;
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
        return null;
    }

    @Override
    public Customer getCustomerByLastName(String lastName) {
        return null;
    }
}
