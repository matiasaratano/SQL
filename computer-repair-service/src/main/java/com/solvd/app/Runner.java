package com.solvd.app;

import com.solvd.app.dao.mysql.CustomerDAO;
import com.solvd.app.dao.mysql.DeviceDAO;
import com.solvd.app.dao.mysql.EmployeeDAO;
import com.solvd.app.dao.mysql.ServiceDAO;
import com.solvd.app.models.*;
import com.solvd.app.service.jdbcimpl.RepairService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class Runner {
    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws SQLException {
        Repair r = new Repair("2021");
        Customer c = new CustomerDAO().getEntityById(3);
        Employee e = new EmployeeDAO().getEntityById(2);
        Service s = new ServiceDAO().getEntityById(2);
        Device d = new DeviceDAO().getEntityById(2);
        //Create new Repair
        Repair rs = new RepairService().createRepair(r, c, e, s, d);
        //Get Repair By Id
        LOGGER.info(new RepairService().getRepairById(rs.getRepairId()));
        //Update Repair
        new RepairService().updateRepair(17, c, e, s, d, "2020");

    }

    public SqlSessionFactory myBatisTask() {
        SqlSessionFactory sqlSessionFactory;
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            LOGGER.error("Error");
            throw new RuntimeException(e);

        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CustomerDAO customerDAO = sqlSession.getMapper(CustomerDAO.class);
        return sqlSessionFactory;
        //sqlSession.commit();
        //sqlSession.rollback();
    }
}