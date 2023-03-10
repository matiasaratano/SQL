package com.solvd.app.dao;

import java.sql.SQLException;

public interface IBaseDAO<T> {
    T getEntityById(int id) throws SQLException;

    void createEntity(T entity) throws SQLException;

    void updateEntity(T entity);

    void removeById(int id);
}
