package com.rochatec.framework.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;

public class Currency implements IFormarter{

	private NumberFormat nf;

	public Currency() {
		nf = DecimalFormat.getCurrencyInstance();
	}

	public String toString(BigDecimal value) {
		value.setScale(2, RoundingMode.HALF_EVEN);
		return nf.format(value);
	}

	public Number parse(String value)throws BadFormatException {
		try {
			return nf.parse(value);
		} catch (ParseException e) {
			throw new BadFormatException();
		}		
	}

	@Override
	public String mask(String value) throws BadFormatException {		
		return null;
	}

	@Override
	public String mask(Object value) throws BadFormatException {
		return nf.format(value);		
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
