package com.solvd.app.dao;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T> {
    T getEntityById(int id);

    T createEntity(T entity);

    void updateEntity(T entity);

    void delete(T entity);

    void removeById(long id);
    //List<T> findAll();
}
