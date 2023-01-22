package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IDeviceDAO;
import com.solvd.app.models.Device;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO extends MySQLDAO implements IDeviceDAO {
    private static final Logger LOGGER = LogManager.getLogger(DeviceDAO.class);
    private final static String GET_DEVICE = "Select * from RepairService.Devices where deviceID=?";
    private final static String GET_ALL_DEVICES = "SELECT * FROM RepairService.Devices";
    private final static String CREATE_DEVICE = "INSERT INTO Devices (DeviceType, Brand) VALUES (?, ?)";
    private final static String UPDATE_DEVICE = "UPDATE RepairService.Devices SET DeviceType = ?, Brand=? WHERE deviceID= ?";
    private final static String DELETE_DEVICE = "DELETE FROM RepairService.Devices WHERE deviceId= ?";
    private final static String DEVICE_BY_REPAIR = "SELECT devices.* FROM devices INNER JOIN repairs ON devices.DeviceID = repairs.DeviceID WHERE repairs.RepairID = ?";
    private final Connection connection;

    public DeviceDAO() throws SQLException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Device getEntityById(int id) throws SQLException {
        Device device = new Device();
        try (PreparedStatement ps = connection.prepareStatement(GET_DEVICE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                device.setId(rs.getInt("deviceID"));
                device.setDeviceType(rs.getString("DeviceType"));
                device.setBrand(rs.getString("Brand"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().close();
        }
        return device;
    }

    @Override
    public Device createEntity(Device entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_DEVICE);

            statement.setString(1, entity.getDeviceType());
            statement.setString(2, entity.getBrand());
            statement.executeUpdate();
            LOGGER.info("Device created.");
            LOGGER.info(entity);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void updateEntity(Device entity) {
        LOGGER.info("Updating device with id " + entity.getId() + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_DEVICE);

            statement.setString(1, entity.getDeviceType());
            statement.setString(2, entity.getBrand());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting device with id " + id + ".");
        try {
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
        try {
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
        try {
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


}
