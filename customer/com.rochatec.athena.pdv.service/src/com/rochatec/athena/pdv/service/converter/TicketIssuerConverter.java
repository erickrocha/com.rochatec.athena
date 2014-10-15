package com.rochatec.athena.pdv.service.converter;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;
import com.rochatec.athena.pdv.service.model.TicketIssuer;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.json.JSONApi;

public class TicketIssuerConverter implements IEntityConverter<TicketIssuer>{

	@Override
	public TicketIssuer toEntity(Map<String, Object> params) {
		JSONApi jsonApi = JSONApi.defaultBuilder().build();
		String serialized = jsonApi.serialize(params);
		return (TicketIssuer) jsonApi.deserialize(serialized,TicketIssuer.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TicketIssuer toEntity(DBObject dbObject) {		
		return toEntity(dbObject.toMap());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> toMap(TicketIssuer ticketIssuer) {
		JSONApi jsonApi = JSONApi.defaultBuilder().build();
		String serialized = jsonApi.serialize(ticketIssuer);
		return ((Map<String, Object>) jsonApi.deserialize(serialized,HashMap.class));
	}

}
