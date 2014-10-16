package com.rochatec.mongo.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.List;
import java.util.Map;

/**
 * Created by erick on 02/10/14.
 */
public interface IDao {

    public void persist(Map<String, Object> mapEntity);

    public void update(Map<String, Object> mapQuery, Map<String, Object> mapEntity);

    public void delete(Map<String, Object> mapEntity);

    DBObject findOne(Map<String, Object> mapEntity);

    List<DBObject> findAll();

    List<DBObject> findKeyValue(Map<String, Object> keyValue);

    public DBCollection getCollection();
}


