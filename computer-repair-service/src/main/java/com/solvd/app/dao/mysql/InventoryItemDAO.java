package com.solvd.app.dao.mysql;

import com.solvd.app.dao.IInventoryItemDAO;
import com.solvd.app.dao.IRepairDAO;
import com.solvd.app.models.InventoryItem;

public class InventoryItemDAO extends MySQLDAO implements IInventoryItemDAO {
    @Override
    public InventoryItem getEntityById(int id) {
        return null;
    }

    @Override
    public InventoryItem createEntity(InventoryItem entity) {
        return null;
    }

    @Override
    public void updateEntity(InventoryItem entity) {

    }

    @Override
    public void delete(InventoryItem entity) {

    }

    @Override
    public void removeById(long id) {

    }
}
