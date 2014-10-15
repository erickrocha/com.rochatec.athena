package com.rochatec.athena.pdv.service.dao.impl;

import com.rochatec.athena.pdv.service.converter.TicketIssuerConverter;
import com.rochatec.athena.pdv.service.dao.ITicketIssuerDao;
import com.rochatec.athena.pdv.service.model.TicketIssuer;
import com.rochatec.mongo.MongoConnection;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.dao.impl.EntityDao;

public class TicketIssuerDaoImpl extends EntityDao<TicketIssuer> implements ITicketIssuerDao{

	public TicketIssuerDaoImpl(MongoConnection client) {
		super(client);
	}

	@Override
	public IEntityConverter<TicketIssuer> getConverter() {
		return new TicketIssuerConverter();
	}

	@Override
	public TicketIssuer findByKey(String key) {
		return null;
	}

}
