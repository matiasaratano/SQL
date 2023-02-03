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

public class CustomerService implements ICustomerDAO {

    private final static Logger LOGGER = LogManager.getLogger(CustomerService.class);
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
        Customer response = new Customer();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICustomerDAO customerDAO = session.getMapper(ICustomerDAO.class);
            response = customerDAO.getEntityById(id);
            LOGGER.info("Get customer OK");
            LOGGER.info(response);
        } catch (SQLException e) {
            LOGGER.info("SQLException trying to get a customer");
        }
        return response;
    }

    @Override
    public Customer createEntity(Customer entity) throws SQLException {
        return null;
    }

    @Override
    public void updateEntity(Customer entity) {

    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomersByRepairId(int repairId) {
        return null;
    }
}
