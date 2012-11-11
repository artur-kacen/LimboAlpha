package com.limbo.app.authentication;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.limbo.app.domain.SystemUser;
import com.limbo.app.service.SystemUserService;
import com.limbo.app.web.ClientController;

@Transactional(readOnly = true)
public class AuthenticationUserDetailsGetter implements UserDetailsService {

	@Autowired
	SystemUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		logger.info("Proverjajem usera: " + username);
		LoggedUser user = null;
		SystemUser systemUser = null;
		try {
			systemUser = userService.getUserByUsername(username);
			List<SimpleGrantedAuthority> grants = userService.listUserAuthority(systemUser.getId());
			logger.info("prova usera: " + grants);
			user = new LoggedUser(username, systemUser.getPassword(), true, true, true, true, grants, systemUser.getId());
		} catch (Exception e) {
			logger.info(e.toString());
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		
		return  user;

	}	

}
