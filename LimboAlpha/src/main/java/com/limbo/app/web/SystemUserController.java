package com.limbo.app.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.limbo.app.authentication.LoggedUser;
import com.limbo.app.domain.SystemUser;
import com.limbo.app.service.SystemUserService;

@Controller
public class SystemUserController {
	
	@Autowired
	SystemUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@RequestMapping("/user")
	public String userRedirect(){
		return "redirect:/user/list";
	}

	@RequestMapping("/user/list")
	public String showAllUsers(Map<String, Object> map) {
		map.put("userList", userService.listUser());
		map.put("userService", userService);		
		
		return "user_list";
	}
	
	@RequestMapping("/user/add")
	public String addUser(Map<String, Object> map) {
		map.put("user", new SystemUser());
		return "user_add";
	}

	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String doAddUser(@ModelAttribute("user") SystemUser user, BindingResult result) {		
		userService.addUser(user);	
		return "redirect:/user/list";

	}
	
	@RequestMapping("/user/delete/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		userService.removeUser(userId);
		return "redirect:/user/list";
	}
	
	@RequestMapping("/user/update/{userId}")
	public String updateUser(Map<String, Object> map, @PathVariable("userId") Integer userId) {
		superAdmin();
		map.put("userId", userId);
		SystemUser user = userService.getUser(userId);
		logger.info("Controller: user roles - " + user.getRoles().toString());
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			LoggedUser loggedUser = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Collection<GrantedAuthority> grants = loggedUser.getAuthorities();
			for (GrantedAuthority aut: grants){
				logger.info(aut.getAuthority());
			}
		}	
		
		map.put("user", user);
		return "user_add";
	}
	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
	private boolean superAdmin(){
		logger.info("SUPERADMIN");
		return true;
	}

	@RequestMapping(value = "/user/update/add", method = RequestMethod.POST)
	public String doUpdateRepair(@ModelAttribute("user") SystemUser user, BindingResult result) {

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			userService.updateUser(user);
		}
		return "redirect:/user/list";

	}
	
	@RequestMapping("/user/encrypt")
	public String encryptPasswords() throws Exception {
		userService.encryptPasswords();
		return "user_list";
	}

}
