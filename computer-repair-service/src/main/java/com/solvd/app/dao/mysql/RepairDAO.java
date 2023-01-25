package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.Repair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDAO extends MySQLDAO implements IRepairDAO {

    private static final Logger LOGGER = LogManager.getLogger(RepairDAO.class);
    private final static String GET_REPAIR = "Select * from RepairService.Repairs where RepairID=?";
    private final static String GET_ALL_REPAIRS = "Select * FROM RepairService.Repairs";
    private final static String CREATE_REPAIR = "INSERT INTO `RepairService`.`Repairs` (`RepairDate`)  VALUES (?)";
    // test OK
    // private final static String CREATE_REPAIR = "INSERT INTO `RepairService`.`Repairs` (customerId, employeeid, serviceid, deviceid,RepairDate)  VALUES (1,1,1,1,?)";
    private final static String UPDATE_REPAIR = "UPDATE RepairService.Repairs SET  RepairDate=? WHERE repairID= ?";
    private final static String DELETE_REPAIR = "DELETE FROM RepairService.Repairs WHERE repairID= ?";


    public RepairDAO() throws SQLException {
    }

    @Override
    public Repair getEntityById(int id) throws SQLException {
        //Connection c = ConnectionPool.getInstance().getConnection();
        Repair repair = new Repair();
        try (PreparedStatement ps = connection.prepareStatement(GET_REPAIR)) {
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
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_REPAIR);
            statement.setString(1, entity.getDate());
            statement.executeUpdate();
            LOGGER.info("Repair created.");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;

    }

    @Override
    public void updateEntity(Repair entity) {
        LOGGER.info("Updating repair with id " + entity.getRepairId() + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_REPAIR);

            statement.setString(1, entity.getDate());
            statement.setInt(2, entity.getRepairId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
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

}
