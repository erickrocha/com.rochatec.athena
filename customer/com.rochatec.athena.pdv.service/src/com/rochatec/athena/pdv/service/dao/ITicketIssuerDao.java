package com.rochatec.athena.pdv.service.dao;

import com.rochatec.athena.pdv.service.model.TicketIssuer;
import com.rochatec.mongo.dao.IEntityDao;

public interface ITicketIssuerDao extends IEntityDao<TicketIssuer>{

	public TicketIssuer findByKey(String key);

}
