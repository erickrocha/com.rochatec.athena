package com.rochatec.pos.athena.persistence.converter;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.DBObject;
import com.rochatec.mongo.converter.IEntityConverter;
import com.rochatec.mongo.json.JSONApi;
import com.rochatec.mongo.json.JSONApiBuilder;
import com.rochatec.pos.athena.persistence.model.TicketPrinter;

public class TicketPrinterConverter implements IEntityConverter<TicketPrinter>{

	@Override
	public TicketPrinter toEntity(Map<String, Object> params) {
		JSONApi jsonApi = new JSONApiBuilder().build();
		String value = jsonApi.serialize(params);
		return (TicketPrinter) jsonApi.deserialize(value, TicketPrinter.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TicketPrinter toEntity(DBObject dbObject) {
		return toEntity(dbObject.toMap());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> toMap(TicketPrinter entity) {
		JSONApi jsonApi = new JSONApiBuilder().build();
		String value = jsonApi.serialize(entity);
		return (HashMap)jsonApi.deserialize(value, HashMap.class);
	}

}
