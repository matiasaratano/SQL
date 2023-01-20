package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IInvoiceDAO;
import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.Invoice;

public class InvoiceDAO extends MySQLDAO implements IInvoiceDAO {
    @Override
    public Invoice getEntityById(int id) {
        return null;
    }

    @Override
    public Invoice createEntity(Invoice entity) {
        return null;
    }

    @Override
    public void updateEntity(Invoice entity) {

    }

    @Override
    public void delete(Invoice entity) {

    }

    @Override
    public void removeById(long id) {

    }
}
