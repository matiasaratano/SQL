package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.IEmployeeDAO;
import com.solvd.app.models.Employee;
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

public class MyBatisEmployeeService implements IEmployeeDAO {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisEmployeeService.class);
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
    public Employee getEntityById(int id) throws SQLException {
        Employee employee = new Employee();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);
            employee = employeeDAO.getEntityById(id);
        } catch (SQLException e) {
            LOGGER.info("SQLException trying to get an employee");
        }
        return employee;
    }


    @Override
    public void createEntity(Employee entity) throws SQLException {
        LOGGER.info("Creating employee..");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEmployeeDAO employeeDAO = sqlSession.getMapper(IEmployeeDAO.class);
            try {
                employeeDAO.createEntity(entity);
                sqlSession.commit();
                LOGGER.info("Employee inserted successfully");
            } catch (Exception e) {
                LOGGER.info("Error inserting employee");
                sqlSession.rollback();
                LOGGER.info("Session rollback");
                LOGGER.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }


    @Override
    public void updateEntity(Employee entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);
            try {
                employeeDAO.updateEntity(entity);
                session.commit();
                LOGGER.info("Employee Updated successfully");
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
                IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);
                employeeDAO.removeById(id);
                LOGGER.info("Employee Deleted");
                session.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }

    @Override
    public List<Employee> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);
            List<Employee> allEmployees = employeeDAO.findAll();
            return allEmployees;
        }
    }

    @Override
    public ArrayList<Employee> getEmployeesByRepairId(int repairId) {
        ArrayList<Employee> employeesList;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IEmployeeDAO employeeDAO = session.getMapper(IEmployeeDAO.class);
            employeesList = employeeDAO.getEmployeesByRepairId(repairId);
            LOGGER.info("Get all employees by repairId finish successfully");
        }
        return employeesList;
    }
}
