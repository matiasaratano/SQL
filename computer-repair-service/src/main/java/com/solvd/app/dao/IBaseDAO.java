package com.solvd.app.dao;

import java.sql.SQLException;

public interface IBaseDAO<T> {
    T getEntityById(int id) throws SQLException;

    T createEntity(T entity);

    void updateEntity(T entity);

    void delete(T entity);

    void removeById(int id);
}
