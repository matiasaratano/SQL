package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.Repair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairDAO extends MySQLDAO implements IRepairDAO {

    private static final Logger LOGGER = LogManager.getLogger(RepairDAO.class);
    private final static String GET_REPAIR = "Select * from RepairService.Repairs where RepairID=?";
    private final static String GET_ALL_REPAIRS = "Select * FROM RepairService.Repairs";
    private final static String CREATE_REPAIR = "INSERT INTO `RepairService`.`Repairs` (customerId, employeeId, serviceId, deviceId, RepairDate)  VALUES (?,?,?,?,?)";
    private final static String UPDATE_REPAIR = "UPDATE RepairService.Repairs SET customerId=?, employeeId=?, serviceId=?, deviceId=?, RepairDate=? WHERE repairID= ?";
    private final static String DELETE_REPAIR = "DELETE FROM RepairService.Repairs WHERE repairID= ?";


    public RepairDAO() throws SQLException {
    }

    @Override
    public Repair getEntityById(int id) throws SQLException {
        Repair repair = new Repair();
        try (Connection connection = MySQLDAO.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_REPAIR)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                repair.setId(rs.getInt("RepairID"));
                repair.setRepairDate(rs.getString("RepairDate"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return repair;
    }

    @Override
    public Repair createEntity(Repair entity) {
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(CREATE_REPAIR, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getRepairCustomer().getCustomerId());
            statement.setInt(2, entity.getRepairEmployees().get(0).getEmployeeId());
            statement.setInt(3, entity.getRepairServices().get(0).getServiceId());
            statement.setInt(4, entity.getRepairDevice().getDeviceId());
            statement.setString(5, entity.getDate());
            statement.executeUpdate();
            LOGGER.info("Repair created.");
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
    public void updateEntity(Repair entity) {
        LOGGER.info("Updating repair with id " + entity.getRepairId() + ".");
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_REPAIR);

            statement.setInt(1, entity.getRepairCustomer().getCustomerId());
            statement.setInt(2, entity.getRepairEmployees().get(0).getEmployeeId());
            statement.setInt(3, entity.getRepairServices().get(0).getServiceId());
            statement.setInt(4, entity.getRepairDevice().getDeviceId());
            statement.setString(5, entity.getDate());
            statement.setInt(6, entity.getRepairId());
            statement.executeUpdate();
            LOGGER.info("Repair updated.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting repair with id " + id + ".");
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(DELETE_REPAIR);

            statement.setInt(1, id);
            statement.executeUpdate();
            LOGGER.info("Repair deleted.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Repair> findAll() {
        LOGGER.info("Finding all Repairs.");
        List<Repair> repairs = new ArrayList<>();
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_REPAIRS);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                repairs.add(new Repair(
                        resultSet.getInt("repairId"),
                        resultSet.getString("repairDate")

                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return repairs;
    }

}
