package com.rochatec.athena.eao.local;

import java.util.List;

import javax.ejb.Local;

import com.rochatec.athena.model.User;

@Local
public interface UserEaoLocal {
	
	public User persist(User user);
	
	public User findById(Long id);
	
	public User findByLogin(String username);
	
	public List<User> findByName(String name);
	
	public void remove(User user);
	
	public boolean isExists(String userName);
	
}
