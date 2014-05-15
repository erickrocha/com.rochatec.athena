package com.rochatec.graphics.bind.converter;

import org.eclipse.core.databinding.conversion.Converter;

import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.formater.IFormarter;

public class StringToMaskConverter extends Converter{
	
	private IFormarter formarter;
	
	public StringToMaskConverter(IFormarter formarter) {
		super(String.class,String.class);
		this.formarter = formarter;
	}
	
	@Override
	public Object convert(Object fromObject) {
		try {
			return formarter.mask(fromObject.toString());
		} catch (BadFormatException e) {
			return fromObject.toString();
		}
	}

}
