package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.Repair;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDAO extends MySQLDAO implements IRepairDAO {

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);
    private final static String GET_REPAIR = "Select * from RepairService.Repairs where RepairID=?";
    private final static String GET_ALL_REPAIRS = "Select * FROM RepairService.Repairs";
    private final static String CREATE_REPAIR = "INSERT INTO `RepairService`.`Repairs` (`CustomerID`, `EmployeeID`, `ServiceID`, `DeviceID`, `RepairDate`)  VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE_REPAIR = "UPDATE RepairService.Repairs SET FirstName = ?, LastName=?, Address = ?, Phone=? WHERE customerID= ?";
    private final static String DELETE_REPAIR = "DELETE FROM RepairService.Repairs WHERE repairID= ?";
    private final Connection connection;

    public RepairDAO() throws SQLException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Repair getEntityById(int id) throws SQLException {
        Connection c = ConnectionPool.getInstance().getConnection();
        Repair repair = new Repair();
        try (PreparedStatement ps = c.prepareStatement(GET_REPAIR)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                repair.setId(rs.getInt("RepairID"));
                repair.setRepairDate(rs.getString("RepairDate"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().close();
        }
        return repair;
    }

    @Override
    public Repair createEntity(Repair entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_REPAIR);

            //statement.setInt(1, entity.get());
            //statement.setInt(2, entity.getLastName());
            //statement.setInt(3, entity.getAddress());
            //statement.setInt(4, entity.getPhone());
            statement.setString(5, entity.getRepairDate());
            statement.executeUpdate();
            LOGGER.info("Repair created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;

    }

    @Override
    public void updateEntity(Repair entity) {

    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting repair with id " + id + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_REPAIR);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Repair> findAll() {
        LOGGER.info("Finding all Repairs.");
        List<Repair> repairs = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_REPAIRS);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                repairs.add(new Repair(
                        resultSet.getString("repairDate")

                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return repairs;
    }


    @Override
    public Repair getRepairByCustomerId(int id) {
        return null;
    }
}
