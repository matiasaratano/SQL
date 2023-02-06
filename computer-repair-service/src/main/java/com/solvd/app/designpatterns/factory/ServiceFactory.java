package com.solvd.app.designpatterns.factory;

import com.solvd.app.service.IRepairService;
import com.solvd.app.service.jdbcimpl.RepairService;
import com.solvd.app.service.mybatisimpl.MyBatisRepairService;
import com.solvd.app.utils.exceptions.IncorrectTypeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ServiceFactory {
    private final static Logger LOGGER = LogManager.getLogger(ServiceFactory.class);

    public IRepairService getService(ImplType type) throws SQLException {
        try {
            if (type == null) {
                throw new IncorrectTypeException("Service type should be declared");
            }
            switch (type) {
                case JDBC:
                    return new RepairService();
                case MYBATIS:
                    return new MyBatisRepairService();
                default:
                    throw new IncorrectTypeException("Unsupported service type. 'JDBC' and 'MYBATIS' types supported only");
            }
        } catch (IncorrectTypeException e) {
            LOGGER.error("Incorrect type exception: ", e);
            return null;
        } catch (SQLException e) {
            LOGGER.error("SQL exception: ", e);
            return null;
        }
    }
}