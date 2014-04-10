package com.rochatec.framework.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.exception.PercentageFormatException;
import com.rochatec.framework.formater.IFormarter;

public class Percentage implements IFormarter{

	private NumberFormat nf;

	public Percentage() {
		nf = NumberFormat.getPercentInstance();
	}

	public String toString(BigDecimal value) {
		value.setScale(2, RoundingMode.HALF_EVEN);
		return nf.format(value);
	}
	
	public String toString(Double value){
		return toString(new BigDecimal(value));
	}

	public Number parse(String value)throws BadFormatException {
		try {
			return nf.parse(value);
		} catch (ParseException e) {
			throw new PercentageFormatException();
		}		
	}

	@Override
	public String mask(String value) throws BadFormatException {
		
		return null;
	}

	@Override
	public String mask(Object value) throws BadFormatException {
		return null;
	}

	@Override
	public String unMask(String value) {
		
		return null;
	}

	@Override
	public String getMessage() {		
		return null;
	}
}
