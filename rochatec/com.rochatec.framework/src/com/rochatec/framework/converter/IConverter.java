package com.rochatec.framework.converter;

import java.util.List;
import java.util.Map;

import com.rochatec.framework.model.Property;

public interface IConverter<T> {
	
	public Map<String,Object> toMap(T object);
	
	public List<String> toList(T object);	
	
	public T toObject(Map<String,Object> map);
	
	public List<Property> toPropertyList(T object);
}
