package com.rochatec.athena.facade.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.rochatec.athena.eao.local.ProfileEaoLocal;
import com.rochatec.athena.eao.local.RoleEaoLocal;
import com.rochatec.athena.eao.local.UserEaoLocal;
import com.rochatec.athena.exceptions.UserLoginException;
import com.rochatec.athena.facade.local.SecurityFacadeLocal;
import com.rochatec.athena.facade.remote.SecurityFacadeRemote;
import com.rochatec.athena.model.Profile;
import com.rochatec.athena.model.Role;
import com.rochatec.athena.model.User;
import com.rochatec.athena.tools.MessageBundleLoader;

@Stateless
public class SecurityFacadeImpl implements SecurityFacadeLocal,
		SecurityFacadeRemote {

	protected static Logger logger = Logger.getLogger(SecurityFacadeImpl.class
			.getName());

	@EJB
	private UserEaoLocal userEaoLocal;

	@EJB
	private ProfileEaoLocal profileEaoLocal;

	@EJB
	private RoleEaoLocal roleEaoLocal;

	public User persist(User user) {
		return userEaoLocal.persist(user);
	}

	public User findUserById(Long id) {
		return userEaoLocal.findById(id);
	}

	public User findUserByLogin(String username) {
		return userEaoLocal.findByLogin(username);
	}

	public List<User> findUserByName(String name) {
		return userEaoLocal.findByName(name);
	}

	public void remove(User user) {
		userEaoLocal.remove(user);
	}

	public List<Role> findAllRoles() {
		return roleEaoLocal.findAll();
	}

	public Role findRoleByKey(String key) {
		return roleEaoLocal.findByKey(key);
	}

	public Profile findProfileById(Long id) {
		return profileEaoLocal.findById(id);
	}

	public List<Profile> findByProfileName(String name) {
		return profileEaoLocal.findByName(name);
	}

	public List<Profile> findAllProfiles() {
		return profileEaoLocal.findAll();
	}

	public User login(String login, String password) throws UserLoginException {
		User user = findUserByLogin(login);
		if (user == null) {
			logger.log(Level.SEVERE,MessageBundleLoader.getMessage("user.login.not.found.exception"));
			throw new UserLoginException("user.login.not.found.exception");
		} else {
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				logger.log(Level.SEVERE,MessageBundleLoader.getMessage("user.login.incorrect.password.exception"));
				throw new UserLoginException(
						"user.login.incorrect.password.exception");
			}
		}
	}

	public void updatePassword(String login, String oldPassword,
			String newPassword) throws UserLoginException {
		User user = findUserByLogin(login);
		if (user == null) {
			logger.log(Level.SEVERE,MessageBundleLoader.getMessage("user.login.not.found.exception"));
			throw new UserLoginException("user.login.not.found.exception");
		}
		if (!user.getPassword().equals(oldPassword)) {
			logger.log(Level.SEVERE,MessageBundleLoader.getMessage("user.login.incorrect.password.exception"));
			throw new UserLoginException(
					"user.login.incorrect.password.exception");
		}
		if (oldPassword.equals(newPassword)) {
			logger.log(Level.SEVERE,MessageBundleLoader.getMessage("user.login.incorrect.new.password.exception"));
			throw new UserLoginException(
					"user.login.incorrect.new.password.exception");
		}
		user.setPassword(newPassword);
		persist(user);
	}

	@Override
	public Profile persist(Profile profile) {
		profile = profileEaoLocal.persist(profile);
		return profile;
	}

	@Override
	public void remove(Profile profile) {
		profileEaoLocal.remove(profile);
	}

    @Override
    public List<User> findAll() {
        return userEaoLocal.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        return userEaoLocal.findByEmail(email);
    }
}
