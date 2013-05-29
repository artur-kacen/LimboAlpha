package com.limbo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limbo.app.dao.RoleDAO;
import com.limbo.app.dao.SystemUserDAO;
import com.limbo.app.domain.DataTablesRequest;
import com.limbo.app.domain.DataTablesResponse;
import com.limbo.app.domain.Role;
import com.limbo.app.domain.SystemUser;
import com.limbo.app.util.OneWayEncryptor;

@Service
public class SystemUserServiceImpl implements SystemUserService {

	@Autowired
	private SystemUserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;
	
	@Transactional("static")
	public void addUser(SystemUser user) {		
		user.setEnabled(true);
		OneWayEncryptor encryptor = new OneWayEncryptor();
		user.setPassword(encryptor.encrypt(user.getPassword(), user.getUsername()));
		Role role = user.getRoles().get(0);
		List<String> authList = new ArrayList<String>();
		switch (role.getAuthority()) {
			case ("ROLE_ADMIN"):{
				authList.add("ROLE_USER");
				break;}
			case ("ROLE_SUPERADMIN"): {
				authList.add("ROLE_USER");
				authList.add("ROLE_ADMIN");
				break;
			}				
		}
		if (authList.size() > 0) {
			List<Role> appendRoles = roleDAO.listRoles(authList);
			user.getRoles().addAll(appendRoles);
		}
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
	
	public DataTablesResponse<SystemUser> getDataTableResponse(DataTablesRequest dtReq){
		return userDAO.getDataTableResponse(dtReq);
	}
}