package com.rochatec.graphics.bind.converter;

import org.eclipse.core.databinding.conversion.Converter;

import com.rochatec.framework.formater.IFormarter;

public class MaskToStringConverter extends Converter{

	private IFormarter formarter;
	
	public MaskToStringConverter(IFormarter formarter) {
		super(String.class,String.class);
		this.formarter = formarter; 
	}

	@Override
	public Object convert(Object fromObject) {
		return formarter.unMask(fromObject.toString());		
	}

	
	
}
