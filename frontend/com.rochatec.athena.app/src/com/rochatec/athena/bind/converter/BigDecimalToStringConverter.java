package com.rochatec.athena.bind.converter;

import java.math.BigDecimal;

import org.eclipse.core.databinding.conversion.Converter;

import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.exception.BadFormatException;

public class BigDecimalToStringConverter extends Converter{

	public BigDecimalToStringConverter() {
		super(BigDecimal.class,String.class);		
	}

	@Override
	public Object convert(Object fromObject) {
		try{
			BigDecimal value = (BigDecimal)fromObject;
			return Formatter.getDecimal().mask(value);
		}catch (BadFormatException ex){
			
		}
		return null;
	}

}
