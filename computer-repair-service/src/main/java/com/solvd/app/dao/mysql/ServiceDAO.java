package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IServiceDAO;
import com.solvd.app.models.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO extends MySQLDAO implements IServiceDAO {
    private static final Logger LOGGER = LogManager.getLogger(ServiceDAO.class);
    private final static String GET_SERVICE = "Select * from RepairService.Services where serviceID=?";
    private final static String GET_ALL_SERVICES = "SELECT * FROM RepairService.Services";
    private final static String CREATE_SERVICE = "INSERT INTO Services (ServiceType, ServicePrice) VALUES (?, ?)";
    private final static String UPDATE_SERVICE = "UPDATE RepairService.Services SET ServiceType = ?, ServicePrice=? WHERE serviceID= ?";
    private final static String DELETE_SERVICE = "DELETE FROM RepairService.Services WHERE serviceId= ?";
    private final static String SERVICE_BY_REPAIR = "SELECT Services.* FROM Services INNER JOIN Repairs ON Services.ServiceID = Repairs.serviceID WHERE Repairs.RepairID = ?";


    public ServiceDAO() throws SQLException {
    }

    @Override
    public Service getEntityById(int id) throws SQLException {
        Service service = new Service();
        try (Connection connection = MySQLDAO.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_SERVICE)) {
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
    public void createEntity(Service entity) {
        try (Connection connection = MySQLDAO.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(CREATE_SERVICE, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, entity.getType());
            statement.setInt(2, entity.getPrice());
            statement.executeUpdate();
            LOGGER.info("Service created.");
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getInt(1));
            }
            LOGGER.info(entity);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

    }

    @Override
    public void updateEntity(Service entity) {
        LOGGER.info("Updating service with id " + entity.getServiceId() + ".");
        try (Connection connection = MySQLDAO.getConnection();) {
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
        try (Connection connection = MySQLDAO.getConnection();) {
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
        try (Connection connection = MySQLDAO.getConnection();) {
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
        try (Connection connection = MySQLDAO.getConnection();) {
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
