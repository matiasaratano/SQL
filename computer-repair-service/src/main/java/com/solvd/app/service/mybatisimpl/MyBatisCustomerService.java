package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.ICustomerDAO;
import com.solvd.app.models.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisCustomerService implements ICustomerDAO {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisCustomerService.class);
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getEntityById(int id) throws SQLException {
        Customer customer = new Customer();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            customer = customerDAO.getEntityById(id);
        } catch (SQLException e) {
            LOGGER.info("SQLException trying to get a customer");
        }
        return customer;
    }


    @Override
    public void createEntity(Customer entity) throws SQLException {
        LOGGER.info("Creating customer..");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            try {
                customerDAO.createEntity(entity);
                sqlSession.commit();
                LOGGER.info("Customer inserted successfully");
            } catch (Exception e) {
                LOGGER.info("Error inserting customer");
                sqlSession.rollback();
                LOGGER.info("Session rollback");
                LOGGER.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }


    @Override
    public void updateEntity(Customer entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            try {
                customerDAO.updateEntity(entity);
                session.commit();
                LOGGER.info("Customer Updated successfully");
            } catch (Exception e) {
                LOGGER.info("Error Updating");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
    }

    @Override
    public void removeById(int id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            if (id > 0) {
                ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
                customerDAO.removeById(id);
                LOGGER.info("Customer Deleted");
                session.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }

    @Override
    public List<Customer> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            List<Customer> allCustomers = customerDAO.findAll();
            return allCustomers;
        }
    }

    @Override
    public ArrayList<Customer> getCustomersByRepairId(int repairId) {
        ArrayList<Customer> customersList;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            customersList = customerDAO.getCustomersByRepairId(repairId);
            LOGGER.info("Get all customers by repairId finish successfully");
        }
        return customersList;
    }
}
