package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IPaymentMethodDAO;
import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.PaymentMethod;

public class PaymentMethodDAO extends MySQLDAO implements IPaymentMethodDAO {
    @Override
    public PaymentMethod getEntityById(int id) {
        return null;
    }

    @Override
    public PaymentMethod createEntity(PaymentMethod entity) {
        return null;
    }

    @Override
    public void updateEntity(PaymentMethod entity) {

    }

    @Override
    public void delete(PaymentMethod entity) {

    }

    @Override
    public void removeById(long id) {

    }
}
