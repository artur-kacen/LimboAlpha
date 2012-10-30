package com.limbo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limbo.app.dao.SystemUserDAO;
import com.limbo.app.domain.SystemUser;

@Service
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserDAO userDAO;

	@Transactional
	public void addUser(SystemUser user) {
		userDAO.addUser(user);
	}

	@Transactional
	public List<SystemUser> listUser() {
		return userDAO.listUser();
	}

	@Transactional
	public void removeUser(Integer id) {
		userDAO.removeUser(id);
	}

	@Transactional
	public SystemUser getUser(Integer id) {
		return userDAO.getUser(id);
	}

	@Transactional
	public void updateUser(SystemUser user) {
		userDAO.updateUser(user);
	}

	@Transactional
	public SystemUser getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}
}