package com.rochatec.graphics.bind.converter;

import java.math.BigDecimal;

import org.eclipse.core.databinding.conversion.Converter;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.math.Decimal;

public class StringToBigDecimalConverter extends Converter{

	public StringToBigDecimalConverter() {
		super(String.class,BigDecimal.class);		
	}

	@Override
	public Object convert(Object fromObject) {
		try{
			IFormarter formarter = new Decimal();			
			return formarter.parse(fromObject.toString());
		}catch (BadFormatException ex){
			
		}
		return null;
	}

}
