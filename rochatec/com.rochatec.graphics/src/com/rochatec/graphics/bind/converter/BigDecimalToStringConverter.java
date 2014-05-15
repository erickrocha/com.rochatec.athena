package com.rochatec.graphics.bind.converter;

import java.math.BigDecimal;

import org.eclipse.core.databinding.conversion.Converter;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;
import com.rochatec.framework.math.Decimal;

public class BigDecimalToStringConverter extends Converter{	
	
	public BigDecimalToStringConverter() {
		super(BigDecimal.class,String.class);		
	}

	@Override
	public Object convert(Object fromObject) {
		try{
			BigDecimal value = (BigDecimal)fromObject;
			IFormarter formarter = new Decimal();
			return formarter.mask(value);
		}catch (BadFormatException ex){
			
		}
		return null;
	}

}
