package com.rochatec.mongo.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.DBCollection;
import com.rochatec.mongo.converter.IEntityConverter;

/**
 * Created by erick on 02/10/14.
 */
public interface IEntityDao<T> {

    public IEntityConverter<T> getConverter();

    public void persist(T entity);

    public void remove(T entity);

    T findOne(Map<String, Object> mapEntity);

    List<T> findAll();

    List<T> findKeyValue(Map<String, Object> keyValue);

    public DBCollection getCollection();
}
