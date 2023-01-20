package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.dao.IVendorDAO;
import com.solvd.app.models.Vendor;

public class VendorDAO extends MySQLDAO implements IVendorDAO {
    @Override
    public Vendor getEntityById(int id) {
        return null;
    }

    @Override
    public Vendor createEntity(Vendor entity) {
        return null;
    }

    @Override
    public void updateEntity(Vendor entity) {

    }

    @Override
    public void delete(Vendor entity) {

    }

    @Override
    public void removeById(long id) {

    }
}
