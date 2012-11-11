package com.limbo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limbo.app.dao.SystemUserDAO;
import com.limbo.app.domain.SystemUser;

@Service
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserDAO userDAO;

	@Transactional("static")
	public void addUser(SystemUser user) {
		userDAO.addUser(user);
	}

	@Transactional("static")
	public List<SystemUser> listUser() {
		return userDAO.listUser();
	}

	@Transactional("static")
	public void removeUser(Integer id) {
		userDAO.removeUser(id);
	}

	@Transactional("static")
	public SystemUser getUser(Integer id) {
		return userDAO.getUser(id);
	}

	@Transactional("static")
	public void updateUser(SystemUser user) {
		userDAO.updateUser(user);
	}

	@Transactional("static")
	public SystemUser getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	public List<SimpleGrantedAuthority> listUserAuthority(Integer id) {
		return userDAO.listUserAuthority(id);
	}
	
	public void  encryptPasswords() throws Exception{
		userDAO.encryptPasswords();
	}
	
}