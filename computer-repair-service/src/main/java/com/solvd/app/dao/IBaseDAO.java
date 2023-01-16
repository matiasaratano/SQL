package com.solvd.app.dao;

import java.util.List;

public interface IBaseDAO<T> {
    T getEntityById(long id);
    T createEntity(T entity);
    void updateEntity(T entity);
    void delete(T entity);
    void removeById(long id);
    //List<T> findAll();
}
