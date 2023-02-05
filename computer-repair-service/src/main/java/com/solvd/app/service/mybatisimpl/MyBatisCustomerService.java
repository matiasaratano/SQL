package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.ICustomerDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.mybatis.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisCustomerService implements ICustomerDAO {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisCustomerService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();


    @Override
    public Customer getEntityById(int id) throws SQLException {
        Customer customer = new Customer();
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customer = customerDAO.getEntityById(id);
        } catch (SQLException e) {
            LOGGER.info("SQLException trying to get a customer");
        }
        return customer;
    }


    @Override
    public void createEntity(Customer entity) throws SQLException {
        LOGGER.info("Creating customer..");
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
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
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            try {
                customerDAO.updateEntity(entity);
                sqlSession.commit();
                LOGGER.info("Customer Updated successfully");
            } catch (Exception e) {
                LOGGER.info("Error Updating");
                sqlSession.rollback();
                LOGGER.info("Session rollback");
            }
        }
    }

    @Override
    public void removeById(int id) {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            if (id > 0) {
                ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
                customerDAO.removeById(id);
                LOGGER.info("Customer Deleted");
                sqlSession.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }

    @Override
    public List<Customer> findAll() {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            List<Customer> allCustomers = customerDAO.findAll();
            return allCustomers;
        }
    }

    @Override
    public ArrayList<Customer> getCustomersByRepairId(int repairId) {
        ArrayList<Customer> customersList;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            ICustomerDAO customerDAO = sqlSession.getMapper(ICustomerDAO.class);
            customersList = customerDAO.getCustomersByRepairId(repairId);
            LOGGER.info("Get all customers by repairId finish successfully");
        }
        return customersList;
    }
}
