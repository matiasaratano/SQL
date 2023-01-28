package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IDeviceDAO;
import com.solvd.app.models.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO extends MySQLDAO implements IDeviceDAO {
    private static final Logger LOGGER = LogManager.getLogger(DeviceDAO.class);
    private final static String GET_DEVICE = "Select * from RepairService.Devices where deviceID=?";
    private final static String GET_ALL_DEVICES = "SELECT * FROM RepairService.Devices";
    private final static String CREATE_DEVICE = "INSERT INTO Devices (DeviceType, Brand) VALUES (?, ?)";
    private final static String UPDATE_DEVICE = "UPDATE RepairService.Devices SET DeviceType = ?, Brand=? WHERE deviceID= ?";
    private final static String DELETE_DEVICE = "DELETE FROM RepairService.Devices WHERE deviceId= ?";
    private final static String DEVICE_BY_REPAIR = "SELECT Devices.* FROM Devices INNER JOIN Repairs ON Devices.DeviceID = Repairs.DeviceID WHERE Repairs.RepairID = ?";


    public DeviceDAO() throws SQLException {
    }

    @Override
    public Device getEntityById(int id) throws SQLException {
        Device device = new Device();
        try (Connection connection = MySQLDAO.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_DEVICE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                device.setId(rs.getInt("deviceID"));
                device.setDeviceType(rs.getString("DeviceType"));
                device.setBrand(rs.getString("Brand"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return device;
    }

    @Override
    public Device createEntity(Device entity) {
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(CREATE_DEVICE, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, entity.getType());
            statement.setString(2, entity.getDeviceBrand());
            statement.executeUpdate();
            LOGGER.info("Device created.");
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            LOGGER.info(entity);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void updateEntity(Device entity) {
        LOGGER.info("Updating device with id " + entity.getDeviceId() + ".");
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_DEVICE);

            statement.setString(1, entity.getType());
            statement.setString(2, entity.getDeviceBrand());
            statement.setInt(3, entity.getDeviceId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting device with id " + id + ".");
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(DELETE_DEVICE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public List<Device> findAll() {
        LOGGER.info("Finding all Devices.");
        List<Device> devices = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_DEVICES);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                devices.add(new Device(
                        resultSet.getInt("deviceId"),
                        resultSet.getString("DeviceType"),
                        resultSet.getString("Brand")

                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return devices;
    }

    @Override
    public ArrayList<Device> getDevicesByRepairId(int repairId) {
        ArrayList<Device> devices = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(
                    DEVICE_BY_REPAIR);
            statement.setInt(1, repairId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Device device = new Device();
                device.setId(resultSet.getInt("DeviceID"));
                device.setDeviceType(resultSet.getString("DeviceType"));
                device.setBrand(resultSet.getString("Brand"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }


    public Device getDeviceByRepairId(int repairId) {
        Device device = new Device();
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(
                    DEVICE_BY_REPAIR);
            statement.setInt(1, repairId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                device.setId(resultSet.getInt("DeviceID"));
                device.setDeviceType(resultSet.getString("DeviceType"));
                device.setBrand(resultSet.getString("Brand"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return device;
    }
}
