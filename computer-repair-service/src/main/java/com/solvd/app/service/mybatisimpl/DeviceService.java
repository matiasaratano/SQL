package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.IDeviceDAO;
import com.solvd.app.models.Device;
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

public class DeviceService implements IDeviceDAO {

    private final static Logger LOGGER = LogManager.getLogger(DeviceService.class);
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
    public Device getEntityById(int id) throws SQLException {
        Device device = new Device();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IDeviceDAO deviceDAO = session.getMapper(IDeviceDAO.class);
            device = deviceDAO.getEntityById(id);
            LOGGER.info("Get device OK");
        } catch (SQLException e) {
            LOGGER.info("SQLException trying to get a customer");
        }
        return device;
    }

    @Override
    public Device createEntity(Device entity) throws SQLException {
        LOGGER.info("Creating device");
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IDeviceDAO deviceDAO = session.getMapper(IDeviceDAO.class);
            try {
                deviceDAO.createEntity(entity);
                session.commit();
                LOGGER.info("Device added successfully");
            } catch (Exception e) {
                LOGGER.info("Error creating device");
                session.rollback();
                LOGGER.info("Session rollback");
            }
        }
        return entity;
    }

    @Override
    public void updateEntity(Device entity) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IDeviceDAO deviceDAO = session.getMapper(IDeviceDAO.class);
            try {
                deviceDAO.updateEntity(entity);
                session.commit();
                LOGGER.info("Device Updated successfully");
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
                IDeviceDAO deviceDAO = session.getMapper(IDeviceDAO.class);
                deviceDAO.removeById(id);
                LOGGER.info("Device Deleted");
                session.commit();
            }
        } catch (Exception e) {
            LOGGER.info("Error Deleting");
        }
    }


    @Override
    public List<Device> findAll() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IDeviceDAO deviceDAO = session.getMapper(IDeviceDAO.class);
            List<Device> allDevices = deviceDAO.findAll();
            return allDevices;
        }
    }

    @Override
    public ArrayList<Device> getDevicesByRepairId(int repairId) {
        ArrayList<Device> devicesList;
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IDeviceDAO deviceDAO = session.getMapper(IDeviceDAO.class);
            devicesList = deviceDAO.getDevicesByRepairId(repairId);
            LOGGER.info("Get all devices by repairId finish successfully");
        }
        return devicesList;
    }
}

