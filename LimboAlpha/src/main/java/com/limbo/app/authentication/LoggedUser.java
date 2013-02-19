package com.limbo.app.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoggedUser extends User {
	
	private Integer userId;
	private String currentDatabase = "skupka";

	public LoggedUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Integer userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.userId = userId;
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

}
