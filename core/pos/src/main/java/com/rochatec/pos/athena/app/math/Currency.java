package com.rochatec.pos.athena.app.math;

import com.rochatec.pos.athena.app.exception.BadFormatException;
import com.rochatec.pos.athena.app.formater.IFormarter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class Currency implements IFormarter{

	private NumberFormat nf;

	public Currency() {
		nf = DecimalFormat.getCurrencyInstance();
	}

	public String toString(BigDecimal value) {
		value.setScale(2, RoundingMode.HALF_EVEN);
		return nf.format(value);
	}

	public BigDecimal parse(String value)throws BadFormatException {
		try {
			return new BigDecimal(nf.parse(value).doubleValue());
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
