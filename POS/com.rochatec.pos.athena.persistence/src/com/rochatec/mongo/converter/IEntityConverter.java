package com.rochatec.mongo.converter;

import com.mongodb.DBObject;

import java.util.Map;

/**
 * Created by erick on 02/10/14.
 */
public interface IEntityConverter<T> {

    public T toEntity(Map<String,Object> params);

    public T toEntity(DBObject dbObject);

    public Map<String,Object> toMap(T entity);

}
