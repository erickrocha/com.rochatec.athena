package com.rochatec.athena.bind.converter;

import java.math.BigDecimal;

import org.eclipse.core.databinding.conversion.Converter;

import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;

public class StringToBigDecimalConverter extends Converter{

	public StringToBigDecimalConverter() {
		super(String.class,BigDecimal.class);		
	}

	@Override
	public Object convert(Object fromObject) {
		try{
			BigDecimal value = Formatter.getDecimal().parse(fromObject.toString());
			return value;
		}catch (BadFormatException ex){
			
		}
		return null;
	}

	

}
