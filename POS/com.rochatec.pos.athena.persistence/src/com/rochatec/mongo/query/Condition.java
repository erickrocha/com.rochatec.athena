package com.rochatec.mongo.query;

import java.util.Collection;
import java.util.Date;

/**
 * Created by epr on 08/10/14.
 */
public class Condition {

    public String field;
    public Operator operator;
    public Object value;
    public Date begin;
    public Date end;
    public boolean caseSensitive;

    public Condition(String field, Operator operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public Condition(String field, Operator operator, Object value,boolean caseSensitive) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.caseSensitive = caseSensitive;
    }

    public Condition(String field, Operator operator, Date begin,Date end) {
        this.field = field;
        this.operator = operator;
        this.begin = begin;
        this.end = end;
    }

    public static Condition create(String field,Operator op,Object value){
        return new Condition(field,op,value);
    }

    public static Condition createEq(String field,Object value){
        return create(field,Operator.EQUAL,value);
    }

    public static Condition createEq(String field,String value){
        return create(field,Operator.EQUAL,value);
    }

    public static Condition createEq(String field,Boolean value){
        return create(field,Operator.EQUAL,value);
    }

    public static Condition createBetween(String field,Date begin,Date end){
        return new Condition(field,Operator.BETWEEN,begin,end);
    }

    public static Condition createLike(String field,String value,Boolean caseSensitive){
        return new Condition(field,Operator.LIKE,value,caseSensitive);
    }

    @SuppressWarnings("rawtypes")
	public static Condition createIn(String field,Collection list,Boolean caseSensitive){
        return new Condition(field,Operator.IN,list,caseSensitive);
    }

    public static Condition createNotEqual(String field,String value,Boolean caseSensitive){
        return new Condition(field,Operator.NOTEQUAL, value,caseSensitive);
    }

    @SuppressWarnings("rawtypes")
	public static Condition createNotIn(String field,Collection list,Boolean caseSensitive){
        return new Condition(field,Operator.NOTIN,list,caseSensitive);
    }

    public static Condition createLike(String field,String value){
        return createLike(field,value,false);
    }

    @SuppressWarnings("rawtypes")
	public static Condition createIn(String field,Collection list){
        return createIn(field,list,false);
    }

    public static Condition createNotEqual(String field,String value){
        return createNotEqual(field,value,false);
    }

    @SuppressWarnings("rawtypes")
	public static Condition createNotIn(String field,Collection list){
        return createNotIn(field,list,false);
    }
}
