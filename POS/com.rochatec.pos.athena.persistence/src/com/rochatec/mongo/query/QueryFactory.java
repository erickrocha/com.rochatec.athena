package com.rochatec.mongo.query;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by epr on 08/10/14.
 */
public class QueryFactory {

    @SuppressWarnings("rawtypes")
	public static DBObject construct(Collection<Condition> conditions,Operator operator){
        List<DBObject> whereList = new ArrayList<DBObject>();

        for (Condition c : conditions){
            if (c.operator.equals(Operator.BETWEEN)){
                DBObject dbObject = QueryBuilder.start(c.field).greaterThanEquals(c.begin).lessThanEquals(c.end).get();
                whereList.add(dbObject);
            }
            if (c.operator.equals(Operator.EQUAL)){
                List<String> equals = new ArrayList<String>();
                equals.add(c.value.toString());
                DBObject dbObject = QueryBuilder.start(c.field).in(equals).get();
                whereList.add(dbObject);
            }
            if (c.operator.equals(Operator.IN)){
                DBObject dbObject = QueryBuilder.start(c.field).in((List)c.value).get();
                whereList.add(dbObject);
            }
            if (c.operator.equals(Operator.NOTEQUAL)){
                DBObject dbObject = QueryBuilder.start(c.field).notEquals(c.value).get();
                whereList.add(dbObject);
            }
            if (c.operator.equals(Operator.NOTIN)){
                DBObject dbObject = QueryBuilder.start(c.field).notIn(c.value).get();
                whereList.add(dbObject);
            }
        }

        QueryBuilder builder = QueryBuilder.start();
        for (DBObject object : whereList){
            if (operator.equals(Operator.AND)){
                builder.and(object);
            }
            if (operator.equals(Operator.OR)){
                builder.or(object);
            }
        }
        return builder.get();
    }

    public static DBObject constructAnd(Collection<Condition> conditions){
        return construct(conditions,Operator.AND);
    }

    public static DBObject constructOr(Collection<Condition> conditions){
        return construct(conditions,Operator.OR);
    }
}
