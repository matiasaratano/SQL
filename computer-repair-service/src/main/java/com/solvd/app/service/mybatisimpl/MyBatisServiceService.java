package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.IServiceDAO;
import com.solvd.app.models.Service;
import com.solvd.app.mybatis.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisServiceService implements IServiceDAO {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisServiceService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();


    @Override
    public Service getEntityById(int id) throws SQLException {
        Service service = new Service();
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IServiceDAO serviceDAO = sqlSession.getMapper(IServiceDAO.class);
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
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
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
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IServiceDAO serviceDAO = sqlSession.getMapper(IServiceDAO.class);
            try {
                serviceDAO.updateEntity(entity);
                sqlSession.commit();
                LOGGER.info("Service Updated successfully");
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
                IServiceDAO serviceDAO = sqlSession.getMapper(IServiceDAO.class);
                serviceDAO.removeById(id);
                LOGGER.info("Service Deleted");
                sqlSession.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }


    @Override
    public List<Service> findAll() {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IServiceDAO serviceDAO = sqlSession.getMapper(IServiceDAO.class);
            List<Service> allServices = serviceDAO.findAll();
            return allServices;
        }
    }

    @Override
    public ArrayList<Service> getServicesByRepairId(int repairId) {
        ArrayList<Service> servicesList;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IServiceDAO serviceDAO = sqlSession.getMapper(IServiceDAO.class);
            servicesList = serviceDAO.getServicesByRepairId(repairId);
            LOGGER.info("Get all services by repairId finish successfully");
        }
        return servicesList;
    }
}

