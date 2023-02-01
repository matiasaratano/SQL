package com.solvd.app;

import com.solvd.app.dao.mysql.CustomerDAO;
import com.solvd.app.dao.mysql.EmployeeDAO;
import com.solvd.app.dao.mysql.ServiceDAO;
import com.solvd.app.models.Customer;
import com.solvd.app.models.Device;
import com.solvd.app.models.Repair;
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
        r.setCustomer(new CustomerDAO().getEntityById(3));
        r.setEmployee(new EmployeeDAO().getEntityById(2));
        //new device
        r.setDevice(new Device("test", "test"));
        r.setService(new ServiceDAO().getEntityById(2));

        //Create new Repair
        new RepairService().createRepair(r);
        //Get Repair By Id
        LOGGER.info(new RepairService().getRepairById(33));
        //Update Repair
        r.setId(7);
        r.setCustomer(new Customer("Matias", "Pepe", "Fake 1234", "999999"));
        new RepairService().updateRepair(r);

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