package com.solvd.app.service.mybatisimpl;

import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.Repair;
import com.solvd.app.mybatis.MyBatisDaoFactory;
import com.solvd.app.service.IRepairService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class MyBatisRepairService implements IRepairService {
    private final static Logger LOGGER = LogManager.getLogger(MyBatisRepairService.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public Repair getRepairById(int repairId) throws SQLException {
        Repair repair;
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IRepairDAO repairDAO = sqlSession.getMapper(IRepairDAO.class);
            repair = repairDAO.getEntityById(repairId);
        }
        return repair;
    }

    @Override
    public Repair createRepair(Repair newRepair) throws SQLException {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            IRepairDAO repairDAO = sqlSession.getMapper(IRepairDAO.class);

            try {
                repairDAO.createEntity(newRepair);

                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                LOGGER.error(e.getMessage(), e);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return newRepair;
    }

    @Override
    public Repair updateRepair(Repair newRepair) throws SQLException {
        return null;
    }
}
