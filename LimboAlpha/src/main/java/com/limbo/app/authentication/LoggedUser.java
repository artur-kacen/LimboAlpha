package com.limbo.app.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoggedUser extends User {
	
	private Integer userId;
	private String currentDatabase = "skupka";
	private String name;
	private String surname;
	private List<String> allowedDatabases;
	
	public LoggedUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Integer userId,
			String name, String surname) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.allowedDatabases = new ArrayList<String>();
		this.allowedDatabases.add("skupka");
		this.allowedDatabases.add("tmp");
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCurrentDatabase() {
		return currentDatabase;
	}

	public void setCurrentDatabase(String currentDatabase) {
		this.currentDatabase = currentDatabase;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
