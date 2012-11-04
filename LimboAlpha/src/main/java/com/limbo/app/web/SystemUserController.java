package com.limbo.app.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.limbo.app.domain.SystemUser;
import com.limbo.app.service.SystemUserService;

@Controller
public class SystemUserController {
	
	@Autowired
	SystemUserService userService;
	
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
		map.put("userId", userId);
		map.put("user", userService.getUser(userId));
		return "user_update";
	}

	@RequestMapping(value = "/user/update/add", method = RequestMethod.POST)
	public String doUpdateRepair(@ModelAttribute("user") SystemUser user, BindingResult result) {

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			userService.updateUser(user);
		}
		return "redirect:/user/list";

	}

}
