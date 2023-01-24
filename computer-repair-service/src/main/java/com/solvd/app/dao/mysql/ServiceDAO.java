package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IServiceDAO;
import com.solvd.app.models.Service;
import com.solvd.app.utils.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO extends MySQLDAO implements IServiceDAO {
    private static final Logger LOGGER = LogManager.getLogger(ServiceDAO.class);
    private final static String GET_SERVICE = "Select * from RepairService.services where serviceID=?";
    private final static String GET_ALL_SERVICES = "SELECT * FROM RepairService.services";
    private final static String CREATE_SERVICE = "INSERT INTO services (ServiceType, ServicePrice) VALUES (?, ?)";
    private final static String UPDATE_SERVICE = "UPDATE RepairService.services SET ServiceType = ?, ServicePrice=? WHERE serviceID= ?";
    private final static String DELETE_SERVICE = "DELETE FROM RepairService.services WHERE serviceId= ?";
    private final static String SERVICE_BY_REPAIR = "SELECT services.* FROM services INNER JOIN repairs ON services.ServiceID = repairs.serviceID WHERE repairs.RepairID = ?";
    private final Connection connection;

    public ServiceDAO() throws SQLException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public Service getEntityById(int id) throws SQLException {
        Service service = new Service();
        try (PreparedStatement ps = connection.prepareStatement(GET_SERVICE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                service.setId(rs.getInt("serviceID"));
                service.setServiceType(rs.getString("ServiceType"));
                service.setServicePrice(rs.getInt("ServicePrice"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return service;
    }

    @Override
    public Service createEntity(Service entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_SERVICE);

            statement.setString(1, entity.getType());
            statement.setInt(2, entity.getPrice());
            statement.executeUpdate();
            LOGGER.info("Service created.");
            LOGGER.info(entity);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return entity;
    }

    @Override
    public void updateEntity(Service entity) {
        LOGGER.info("Updating service with id " + entity.getServiceId() + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SERVICE);
            statement.setString(1, entity.getType());
            statement.setInt(2, entity.getPrice());
            statement.setInt(3, entity.getServiceId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void removeById(int id) {
        LOGGER.info("Deleting service with id " + id + ".");
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_SERVICE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public List<Service> findAll() {
        LOGGER.info("Finding all Services.");
        List<Service> services = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_SERVICES);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                services.add(new Service(
                        resultSet.getInt("serviceId"),
                        resultSet.getString("ServiceType"),
                        resultSet.getInt("ServicePrice")

                ));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return services;
    }

    @Override
    public ArrayList<Service> getServicesByRepairId(int repairId) {
        ArrayList<Service> services = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    SERVICE_BY_REPAIR);
            statement.setInt(1, repairId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getInt("ServiceID"));
                service.setServiceType(resultSet.getString("ServiceType"));
                service.setServicePrice(resultSet.getInt("ServicePrice"));
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}
