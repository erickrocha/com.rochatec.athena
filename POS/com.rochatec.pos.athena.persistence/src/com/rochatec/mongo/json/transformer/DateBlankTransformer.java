package com.rochatec.mongo.json.transformer;

import flexjson.JSONException;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;
import flexjson.transformer.AbstractTransformer;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by epr on 07/10/14.
 */
public class DateBlankTransformer extends AbstractTransformer implements ObjectFactory {

    private String dateFormat;
    private ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>();

    public DateBlankTransformer(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Object instantiate(ObjectBinder objectBinder, Object value, Type type, Class aClass) {
        try {
            return value != null ? getFormatter().parse(value.toString()) : null;
        } catch (ParseException e) {
            throw new JSONException(String.format( "%s: Failed to parse %s with %s pattern.", objectBinder.getCurrentPath(), value, dateFormat ), e );
        }
    }

    @Override
    public void transform(Object value) {
        getContext().writeQuoted(getFormatter().format(value));
    }

    private SimpleDateFormat getFormatter() {
        if( formatter.get() == null ) {
            formatter.set( new SimpleDateFormat(dateFormat) );
        }
        return formatter.get();
    }
}
