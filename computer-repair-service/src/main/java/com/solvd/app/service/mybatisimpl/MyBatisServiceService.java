package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.IServiceDAO;
import com.solvd.app.models.Service;
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

public class MyBatisServiceService implements IServiceDAO {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisServiceService.class);
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
    public Service getEntityById(int id) throws SQLException {
        Service service = new Service();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IServiceDAO serviceDAO = session.getMapper(IServiceDAO.class);
            service = serviceDAO.getEntityById(id);
            LOGGER.info("Get service OK");
        } catch (SQLException e) {
            LOGGER.info("SQLException trying to get a service");
        }
        return service;
    }

    @Override
    public void createEntity(Service entity) throws SQLException {
        LOGGER.info("Creating service..");
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IServiceDAO serviceDAO = sqlSession.getMapper(IServiceDAO.class);
            try {
                serviceDAO.createEntity(entity);
                sqlSession.commit();
                LOGGER.info("Service inserted successfully");
            } catch (Exception e) {
                LOGGER.info("Error inserting service");
                sqlSession.rollback();
                LOGGER.info("Session rollback");
                LOGGER.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public void updateEntity(Service entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IServiceDAO serviceDAO = session.getMapper(IServiceDAO.class);
            try {
                serviceDAO.updateEntity(entity);
                session.commit();
                LOGGER.info("Service Updated successfully");
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
                IServiceDAO serviceDAO = session.getMapper(IServiceDAO.class);
                serviceDAO.removeById(id);
                LOGGER.info("Service Deleted");
                session.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }


    @Override
    public List<Service> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IServiceDAO serviceDAO = session.getMapper(IServiceDAO.class);
            List<Service> allServices = serviceDAO.findAll();
            return allServices;
        }
    }

    @Override
    public ArrayList<Service> getServicesByRepairId(int repairId) {
        ArrayList<Service> servicesList;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IServiceDAO serviceDAO = session.getMapper(IServiceDAO.class);
            servicesList = serviceDAO.getServicesByRepairId(repairId);
            LOGGER.info("Get all services by repairId finish successfully");
        }
        return servicesList;
    }
}

