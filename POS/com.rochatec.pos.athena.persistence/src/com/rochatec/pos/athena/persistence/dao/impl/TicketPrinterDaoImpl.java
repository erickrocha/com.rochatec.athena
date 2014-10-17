package com.rochatec.pos.athena.persistence.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.rochatec.mongo.MongoConnection;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.dao.impl.EntityDao;
import com.rochatec.pos.athena.persistence.converter.TicketPrinterConverter;
import com.rochatec.pos.athena.persistence.dao.TicketPrinterDao;
import com.rochatec.pos.athena.persistence.model.TicketPrinter;

public class TicketPrinterDaoImpl extends EntityDao<TicketPrinter> implements TicketPrinterDao{

	public TicketPrinterDaoImpl(MongoConnection client) {
		super(client);		
	}

	@Override
	public IEntityConverter<TicketPrinter> getConverter() {
		return new TicketPrinterConverter();
	}

	@Override
	public TicketPrinter findBySerialNumber(String serialNumber) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("serialNumber",serialNumber);
		return super.findOne(params);
	}

}
