package com.rochatec.mongo.converter.impl;

import com.mongodb.DBObject;
import com.rochatec.mongo.converter.IEntityConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by epr on 03/10/14.
 */
public class DefaultConverter implements IEntityConverter<Map<String,Object>> {

    @Override
    public Map<String, Object> toEntity(Map<String, Object> params) {
        return params;
    }

    @Override
    public Map<String, Object> toEntity(DBObject dbObject) {
        Map<String,Object> map = new HashMap<String, Object>();
        for (String key :dbObject.keySet()){
            map.put(key,dbObject.get(key));
        }
        return map;
    }

    @Override
    public Map<String, Object> toMap(Map<String, Object> entity) {
        return entity;
    }
}
