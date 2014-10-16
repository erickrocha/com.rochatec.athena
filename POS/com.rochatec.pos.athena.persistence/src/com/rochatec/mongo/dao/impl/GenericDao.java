package com.rochatec.mongo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.rochatec.mongo.MongoConnection;
import com.rochatec.mongo.dao.IDao;

/**
 * Created by erick on 02/10/14.
 */
public class GenericDao implements IDao {

    private DBCollection dbCollection;


    public GenericDao(MongoConnection client,String collection) {
        this.dbCollection = client.getDb().getCollection(collection);
    }

    @Override
    public void persist(Map<String, Object> mapEntity) {
        BasicDBObject document = new BasicDBObject(mapEntity);
        dbCollection.save(document);
    }

    @Override
    public void update(Map<String, Object> mapQuery, Map<String, Object> mapEntity) {
        BasicDBObject query = new BasicDBObject(mapQuery);
        BasicDBObject entity = new BasicDBObject(mapEntity);
        dbCollection.update(query, entity);
    }

    @Override
    public void delete(Map<String, Object> mapEntity) {
        BasicDBObject entity = new BasicDBObject(mapEntity);
        dbCollection.remove(entity);
    }

    @Override
    public DBObject findOne(Map<String, Object> mapEntity) {
        BasicDBObject query = new BasicDBObject(mapEntity);
        return dbCollection.findOne(query);
    }

    @Override
    public List<DBObject> findAll() {
        List<DBObject> list = new ArrayList<DBObject>();
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list;
    }

    @Override
    public List<DBObject> findKeyValue(Map<String, Object> keyValue) {
        List<DBObject> list = new ArrayList<DBObject>();
        DBCursor cursor = dbCollection.find(new BasicDBObject(keyValue));
        while (cursor.hasNext()) {
            list.add(cursor.next());
        }
        return list;
    }

    @Override
    public DBCollection getCollection() {
        return dbCollection;
    }
}
