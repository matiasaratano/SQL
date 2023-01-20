package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IPaymentDAO;
import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.Payment;

public class PaymentDAO extends MySQLDAO implements IPaymentDAO {
    @Override
    public Payment getEntityById(int id) {
        return null;
    }

    @Override
    public Payment createEntity(Payment entity) {
        return null;
    }

    @Override
    public void updateEntity(Payment entity) {

    }

    @Override
    public void delete(Payment entity) {

    }

    @Override
    public void removeById(long id) {

    }
}
