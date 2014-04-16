package com.rochatec.athena.address.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rochatec.athena.app.Activator;
import com.rochatec.athena.i18n.Messages;
import com.rochatec.athena.model.Address;
import com.rochatec.athena.util.Formatter;
import com.rochatec.framework.converter.IConverter;
import com.rochatec.framework.exception.BadFormatException;
import com.rochatec.framework.model.Property;


public class AddressConverter implements IConverter<Address>{

	@Override
	public Map<String, Object> toMap(Address address) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Messages.getMessage("address.field.label.zipcode"),address.getZipcode());
		map.put(Messages.getMessage("address.field.label.street"),address.getStreet());
		map.put(Messages.getMessage("address.field.label.streetNumber"),address.getAddressNumber());
		map.put(Messages.getMessage("address.field.label.complement"),"");
		map.put(Messages.getMessage("address.field.label.neighborhood"),address.getNeighborhood());
		map.put(Messages.getMessage("address.field.label.city"),address.getCity());
		map.put(Messages.getMessage("address.field.label.province"),address.getProvince().getAcronym());
		return null;
	}

	@Override
	public List<String> toList(Address object) {
		return null;
	}

	@Override
	public Address toObject(Map<String, Object> map) {
		
		return null;
	}

	@Override
	public List<Property> toPropertyList(Address address) {
		List<Property> properties = new ArrayList<Property>();
		try {
			properties.add(new Property(Messages.getMessage("address.field.label.zipcode"),
					Formatter.getZipCode().mask(address.getZipcode()),0));			
		} catch (BadFormatException e) {
			Activator.getDefault().addConsoleError(e);
		}
		properties.add(new Property(Messages.getMessage("address.field.label.street"),address.getStreet(),1));
		properties.add(new Property(Messages.getMessage("address.field.label.streetNumber"),address.getAddressNumber(),2));
		properties.add(new Property(Messages.getMessage("address.field.label.complement"),"",3));
		properties.add(new Property(Messages.getMessage("address.field.label.neighborhood"),address.getNeighborhood(),4));
		properties.add(new Property(Messages.getMessage("address.field.label.city"),address.getCity(),5));
		properties.add(new Property(Messages.getMessage("address.field.label.province"),address.getProvince() != null ? address.getProvince().getAcronym() : "",6));
		return properties;
	}

}
