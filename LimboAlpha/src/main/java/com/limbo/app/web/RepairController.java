package com.limbo.app.web;

import java.util.Map;


import com.limbo.app.domain.Repair;
import com.limbo.app.service.RepairService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class RepairController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private RepairService repairService;	
	
	@RequestMapping("/repair/add")
	public String addRepair(Map<String, Object> map) {
		map.put("repair", new Repair());
		//map.put("repairList", repairService.listRepair());
		return "repair_add";
	}

	@RequestMapping(value = "/repair/add", method = RequestMethod.POST)
	public String doAddRepair(@ModelAttribute("repair") Repair repair,
			BindingResult result) {

		repairService.addRepair(repair);

		return "redirect:/repair/list";
	}
	
	@RequestMapping("/repair/list")
	public String listRepair(Map<String, Object> map) {		
		map.put("repairList", repairService.listRepair());
		return "repair_list";
	}

	/*@RequestMapping("/delete/{clientId}")
	public String deleteClient(@PathVariable("clientId") Integer clientId) {
		logger.info("Client ID: " + clientId);
		clientService.removeClient(clientId);

		return "redirect:/clients";
	}*/

}
