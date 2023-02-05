package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.IDeviceDAO;
import com.solvd.app.models.Device;
import com.solvd.app.mybatis.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisDeviceService implements IDeviceDAO {

    private final static Logger LOGGER = LogManager.getLogger(MyBatisDeviceService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();


    @Override
    public Device getEntityById(int id) throws SQLException {
        Device device = new Device();
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IDeviceDAO deviceDAO = sqlSession.getMapper(IDeviceDAO.class);
            device = deviceDAO.getEntityById(id);
            LOGGER.info("Get device OK");
        } catch (SQLException e) {
            LOGGER.info("SQLException trying to get a device");
        }
        return device;
    }

    @Override
    public void createEntity(Device entity) throws SQLException {
        LOGGER.info("Creating device..");
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IDeviceDAO deviceDAO = sqlSession.getMapper(IDeviceDAO.class);
            try {
                deviceDAO.createEntity(entity);
                sqlSession.commit();
                LOGGER.info("Device inserted successfully");
            } catch (Exception e) {
                LOGGER.info("Error inserting device");
                sqlSession.rollback();
                LOGGER.info("Session rollback");
                LOGGER.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public void updateEntity(Device entity) {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IDeviceDAO deviceDAO = sqlSession.getMapper(IDeviceDAO.class);
            try {
                deviceDAO.updateEntity(entity);
                sqlSession.commit();
                LOGGER.info("Device Updated successfully");
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
                IDeviceDAO deviceDAO = sqlSession.getMapper(IDeviceDAO.class);
                deviceDAO.removeById(id);
                LOGGER.info("Device Deleted");
                sqlSession.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }


    @Override
    public List<Device> findAll() {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IDeviceDAO deviceDAO = sqlSession.getMapper(IDeviceDAO.class);
            List<Device> allDevices = deviceDAO.findAll();
            return allDevices;
        }
    }

    @Override
    public ArrayList<Device> getDevicesByRepairId(int repairId) {
        ArrayList<Device> devicesList;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IDeviceDAO deviceDAO = sqlSession.getMapper(IDeviceDAO.class);
            devicesList = deviceDAO.getDevicesByRepairId(repairId);
            LOGGER.info("Get all devices by repairId finish successfully");
        }
        return devicesList;
    }
}

