package com.rochatec.athena.eao.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GenericEao<T,ID extends Serializable> {
	
	@PersistenceContext(unitName = "AthenaPersistence")
	private EntityManager entityManager;
	
	private Class<T> entityBeanType;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericEao() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		ParameterizedType type = (ParameterizedType) genericSuperclass;
		this.entityBeanType = ((Class) type.getActualTypeArguments()[0]);
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected EntityManager getEntityManager() {
		if (this.entityManager == null) {
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		}
		return this.entityManager;
	}
	
	protected Class<T> getEntityBeanType() {
		return this.entityBeanType;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id){
		Object entity = getEntityManager().find(getEntityBeanType(), id);
		return (T) entity;
	}
	
	public T persist(T entity) {
		return getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(entity);
	}
	
}
