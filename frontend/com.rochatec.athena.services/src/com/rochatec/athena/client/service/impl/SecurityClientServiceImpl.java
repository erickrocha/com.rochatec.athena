package com.rochatec.athena.client.service.impl;

import java.util.List;

import org.jboss.logging.Logger;

import com.rochatec.athena.client.ServiceLocator;
import com.rochatec.athena.client.service.SecurityClientService;
import com.rochatec.athena.exceptions.UserException;
import com.rochatec.athena.exceptions.UserLoginException;
import com.rochatec.athena.exceptions.UserNotLoggedException;
import com.rochatec.athena.facade.remote.SecurityFacadeRemote;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.User;

public class SecurityClientServiceImpl implements SecurityClientService {
	
	private SecurityFacadeRemote serviceRemote = ServiceLocator.getInstance().getSecurityFacadeRemote();
	private static final Logger LOGGER = Logger.getLogger(SecurityClientServiceImpl.class);
	private static final boolean IS_DEBUG_ENABLE = LOGGER.isDebugEnabled();
	
	
	public User persist(User user) {
		return serviceRemote.persist(user);
	}

	
	public User findUserById(Long id) {
		return serviceRemote.findUserById(id);
	}

	
	public User findUserByLogin(String username) {
		return serviceRemote.findUserByLogin(username);
	}

	
	public List<User> findUserByName(String name) {
		return serviceRemote.findUserByName(name);
	}

	
	public void remove(User user) {
		serviceRemote.remove(user);		
	}

	
	public List<Role> findAllRoles() {
		return serviceRemote.findAllRoles();
	}

	
	public Role findRoleByKey(String key) {
		return serviceRemote.findRoleByKey(key);
	}

	
	public Profile findProfileById(Long id) {
		return serviceRemote.findProfileById(id);
	}

	
	public List<Profile> findByProfileName(String name) {
		return serviceRemote.findByProfileName(name);
	}

	
	public List<Profile> findAllProfiles() {
		return serviceRemote.findAllProfiles();
	}

	
	public User login(String login, String password) throws UserLoginException, UserNotLoggedException {
		if (IS_DEBUG_ENABLE){
			LOGGER.info("Iniciando processo de login");
		}
		User user = null;
		try {
			user = serviceRemote.login(login, password);
		} catch (UserException e) {
			LOGGER.error(e);
			throw new UserNotLoggedException();
		}
		if (IS_DEBUG_ENABLE){
			LOGGER.info("Finalizando processo de login");
		}
		return user;
	}

	
	public void updatePassword(String login, String oldPassword,
			String newPassword) throws UserLoginException {
		serviceRemote.updatePassword(login, oldPassword,newPassword);
	}


	@Override
	public Profile persist(Profile profile) {
		return serviceRemote.persist(profile);
	}


	@Override
	public void remove(Profile profile) {
		serviceRemote.remove(profile);
	}

}
