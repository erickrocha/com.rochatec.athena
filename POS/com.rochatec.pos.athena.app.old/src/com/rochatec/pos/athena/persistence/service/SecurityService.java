package com.rochatec.pos.athena.persistence.service;

import java.util.List;

import com.rochatec.pos.athena.exception.UserLoginException;
import com.rochatec.pos.athena.persistence.model.Profile;
import com.rochatec.pos.athena.persistence.model.Role;
import com.rochatec.pos.athena.persistence.model.User;

public interface SecurityService {

	public User persist(User user);

	public User findUserById(Long id);

	public User findUserByLogin(String username);

	public List<User> findUserByName(String name);

	public void remove(User user);

	public List<Role> findAllRoles();

	public Role findRoleByKey(String key);

	public Profile findProfileById(Long id);

	public List<Profile> findByProfileName(String name);

	public List<Profile> findAllProfiles();
	
	public User login(String login, String password) throws UserLoginException ;
	
	public void updatePassword(String login, String oldPassword, String newPassword) throws UserLoginException;
	
	public Profile persist(Profile profile);
	
	public void remove(Profile profile);
}
