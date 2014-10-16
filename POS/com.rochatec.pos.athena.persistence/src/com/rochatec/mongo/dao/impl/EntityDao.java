package com.rochatec.mongo.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.rochatec.mongo.MongoConnection;
import com.rochatec.mongo.dao.IEntityDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by erick on 02/10/14.
 */
public abstract class EntityDao<T> implements IEntityDao<T> {

    private Class<T> persistentClass;
    private DBCollection dbCollection;


    @SuppressWarnings({ "unchecked", "rawtypes" })
	public EntityDao(MongoConnection client) {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType type = (ParameterizedType) genericSuperclass;
        this.persistentClass = ((Class) type.getActualTypeArguments()[0]);
        this.dbCollection = client.getDb().getCollection(persistentClass.getSimpleName());
    }

    @Override
    public void persist(T entity) {
        BasicDBObject document = new BasicDBObject(getConverter().toMap(entity));
        dbCollection.save(document);
    }

    @Override
    public void remove(T entity) {
        BasicDBObject document = new BasicDBObject(getConverter().toMap(entity));
        dbCollection.remove(document);
    }

    @Override
    public T findOne(Map<String, Object> mapEntity) {
        BasicDBObject query = new BasicDBObject(mapEntity);
        return getConverter().toEntity(dbCollection.findOne(query));
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            list.add(getConverter().toEntity(cursor.next()));
        }
        return list;
    }

    @Override
    public List<T> findKeyValue(Map<String, Object> keyValue) {
        List<T> list = new ArrayList<T>();
        DBCursor cursor = dbCollection.find(new BasicDBObject(keyValue));
        while (cursor.hasNext()) {
            list.add(getConverter().toEntity(cursor.next()));
        }
        return list;
    }

    @Override
    public DBCollection getCollection() {
        return dbCollection;
    }
}
