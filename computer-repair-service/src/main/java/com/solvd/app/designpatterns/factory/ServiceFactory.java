package com.solvd.app.designpatterns.factory;

import com.solvd.app.service.IRepairService;
import com.solvd.app.service.jdbcimpl.RepairService;
import com.solvd.app.service.mybatisimpl.MyBatisRepairService;
import com.solvd.app.utils.exceptions.IncorrectTypeException;

import java.sql.SQLException;

public class ServiceFactory {

    public IRepairService getService(ImplType type) throws SQLException {
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
    }
}