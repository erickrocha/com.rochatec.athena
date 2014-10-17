package com.rochatec.mongo.test;

import com.mongodb.DBObject;
import com.rochatec.mongo.MongoConnection;
import com.rochatec.mongo.dao.impl.GenericDao;

public class TestConnection {

	public static void main(String[] args){
		MongoConnection connection = MongoConnection.getInstance("localhost","obrigacoes-liberada-producao");
		GenericDao genericDao = new GenericDao(connection,"vencimento");
		for (DBObject object : genericDao.findAll()){
			System.out.println(object);
		}
	}
}
