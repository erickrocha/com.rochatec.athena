package com.rochatec.athena.eao.impl;

import com.rochatec.athena.eao.local.UserEaoLocal;
import com.rochatec.athena.eao.util.GenericEao;
import com.rochatec.athena.model.User;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class UserEaoImpl extends GenericEao<User, Serializable> implements
		UserEaoLocal {

	public User findById(Long id) {
		User user = super.findById(id);
		return user;
	}

	public User findByLogin(String username) {
		Query query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.username = :username");
		query.setParameter("username", username);
		User user = (User) query.getSingleResult();
		return user;
	}

    public User findByEmail(String username) {
        Query query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.email = :email");
        query.setParameter("email", username);
        User user = (User) query.getSingleResult();
        return user;
    }

	@SuppressWarnings("unchecked")
	public List<User> findByName(String name) {
		Query query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.name = :name");
		query.setParameter("name", name);
		Set<User> temp = new HashSet<User>(query.getResultList());
		List<User> users = new ArrayList<User>(temp);
		return users;
	}

	public boolean isExists(String userName) {
		if (findByLogin(userName) != null)
			return true;
		return false;
	}

    public List<User> findAll() {
        Query query = getEntityManager().createQuery("SELECT u FROM User u");
        @SuppressWarnings("unchecked")
		List<User> users = ((List<User>)query.getResultList());
        return users;
    }
}
