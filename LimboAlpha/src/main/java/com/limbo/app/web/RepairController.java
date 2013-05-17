package com.limbo.app.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.limbo.app.authentication.LoggedUser;
import com.limbo.app.domain.DataTablesRequest;
import com.limbo.app.domain.DataTablesResponse;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.RepairJson;
import com.limbo.app.domain.SystemUser;
import com.limbo.app.pdf.RepairPDFGeneration;
import com.limbo.app.service.RepairService;
import com.limbo.app.service.SystemUserService;

@Controller
public class RepairController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	private Boolean isRepaired = null;
	private Boolean isReturned = null;
	@Autowired
	private RepairService repairService;

	@Autowired
	private SystemUserService userService;

	@RequestMapping("/repair")
	public String redirectRepair(){
		return "redirect:/repair/list";
	}
	
	@RequestMapping("/repair/add")
	public String addRepair(Map<String, Object> map) {
		map.put("repair", new Repair());
		return "repair_add";
	}

	@RequestMapping(value = "/repair/add", method = RequestMethod.POST)
	public String doAddRepair(@ModelAttribute("repair") Repair repair, BindingResult result) {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			LoggedUser user = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			logger.info("(Add or Update) Repair id: " + repair.getId());
			if (repair.getId() == null) {
				repairService.addRepair(repair, user.getUserId());
			} else {
				repairService.updateRepair(repair);
			}
			
		}
		return "redirect:/repair/list";

	}
	
//	@RequestMapping("/repair/update/{repairId}")
//	public String updateRepair(Map<String, Object> map,
//			@PathVariable("repairId") Integer repairId) {
//		logger.info("Showing repair ID: " + repairId);
//		map.put("repairId", repairId);
//		map.put("repair", repairService.getRepair(repairId));
//		return "repair_add";
//	}
//
//	@RequestMapping(value = "/repair/update/add", method = RequestMethod.POST)
//	public String doUpdateRepair(
//			@ModelAttribute("repair") Repair repair, BindingResult result) {
//
//		if (SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal() instanceof User) {
//			repairService.updateRepair(repair);
//		}
//		return "redirect:/repair/list";
//
//	}
	
	@RequestMapping(value = "/repair/doajax.do", method = RequestMethod.POST)
	public @ResponseBody
	DataTablesResponse<Repair> getData(@RequestBody DataTablesRequest dtReq, Boolean isReturned, Boolean isRepaired) {
		logger.info("Repair ajax request.");
		DataTablesResponse<Repair> dtResp = repairService.getDataTableResponse(dtReq, this.isReturned, this.isRepaired);		
		return dtResp;
	}	
  
//	@RequestMapping(value = "/repair/add.request", method = RequestMethod.POST)
//	public @ResponseBody String requestNewRepair(Map<String, Object> map){
//		map.put("repair", new Repair());
//		return "{\"status\": \"OK\"}";
//	}
	
	@RequestMapping(value = "/repair/test.do", method = RequestMethod.POST)
	public @ResponseBody String ajaxRequest(@RequestBody JsonNode json, Map<String, Object> map){
		String failed = "{\"status\": \"FAILED\"}";
		String success = "{\"status\": \"OK\"}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			RepairJson repairRequest = mapper.readValue(json, RepairJson.class);
			Repair repair = repairService.getRepair(repairRequest.getId());
			switch (repairRequest.getMethod()) {
			case "edit": {
				map.put("repair", new Repair());
			}break;
			case "fixed": {
				if (repair.getRepairDate() == null) {
					repairService.repairRepair(repairRequest.getId());
				}
			}break;
			case "returned": {
				if (!repairService.isReturned(repair)) {
					repairService.approveRepair(repairRequest.getId());
				}
			}break;				
			case "deleted": {
				repairService.removeRepair(repairRequest.getId());
			}break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return failed;
		}
		
		return success;
	}
	
	@RequestMapping(value = "/repair/edit.do", method = RequestMethod.POST)
	public @ResponseBody Repair ajaxEditRequest(@RequestBody JsonNode json, Map<String, Object> map){
		ObjectMapper mapper = new ObjectMapper();
		try {
			RepairJson repairRequest = mapper.readValue(json, RepairJson.class);			
			if (repairRequest.getMethod().equals("edit")) {
				Repair repair = repairService.getRepair(repairRequest.getId());
				return repair;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/repair/list")
	public String listRepairs(Map<String, Object> map) {
		//return "redirect:/repair/list/new"; 
		clearStatus();
		map.put("repair", new Repair());
		return "repair_json";
	}
	
	
	@RequestMapping(value="/repair/list", method=RequestMethod.GET, params="show")	
	public String listShowRepairs(Map<String, Object> map, @RequestParam("show") String show) {		
		logger.info("Show only: " + show);
		clearStatus();
		switch (show) {
		case "new":
			this.isRepaired = false;
			break;
		case "repaired":
			this.isRepaired = true;
			break;
		case "returned":
			this.isReturned = true;
			break;
		default:
			break;
		}
		map.put("repair", new Repair());
		return "repair_json";
	}
	
	private void clearStatus() {
		this.isRepaired = null;
		this.isReturned = null;
	}
	
	
	/*@RequestMapping("/repair/list/all")
	public String listAllRepair(Map<String, Object> map) {
		map.put("repairList", repairService.listRepair());
		map.put("repairService", repairService);
		return "repair_list";
	}
	
	@RequestMapping("/repair/list/new")
	public String listNewRepair(Map<String, Object> map) {
		map.put("repairList", repairService.listDoneOrNewRepairs(false));
		map.put("repairService", repairService);
		return "repair_list";
	}
	
	@RequestMapping("/repair/list/repaired")
	public String listRepairedRepair(Map<String, Object> map) {
		map.put("repairList", repairService.listDoneOrNewRepairs(true));
		map.put("repairService", repairService);
		return "repair_list";
	}
	
	@RequestMapping("/repair/list/returned")
	public String listReturnedRepair(Map<String, Object> map) {
		map.put("repairList", repairService.listReturnedRepair(true));
		map.put("repairService", repairService);
		return "repair_list";
	}
	
	@RequestMapping("/repair/list/notreturned")
	public String listNotReturnedRepair(Map<String, Object> map) {
		map.put("repairList", repairService.listReturnedRepair(false));
		map.put("repairService", repairService);
		return "repair_list";
	}
	
	@RequestMapping("/repair/list/deleted")
	public String listDeletedRepairs(Map<String, Object> map) {
		map.put("deletedRepairList", repairService.listDeletedRepairs());
		map.put("repairService", repairService);
		return "repair_deleted";
	}*/
	
	
	@RequestMapping("/repair/list/getpdf/{repairId}")
	public String getPDF(@PathVariable("repairId") Integer repairId,
			Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("PDF generation for ID: " + repairId);
		
		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal() instanceof User) {
			RepairPDFGeneration pdf = new RepairPDFGeneration();
			User user = (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			String username = user.getUsername();
			SystemUser currentUser = userService.getUserByUsername(username);
			Repair repair = repairService.getRepair(repairId); 
			SystemUser creator = userService.getUser(repair.getUserId());
			try {
				pdf.fill(request, response, repair, currentUser, creator);
			} catch (Exception e) {
				logger.info("Failed 2: " + e);
			}
		}
		return "repear_list";
	}
	
	/*@RequestMapping("/repair/approve/{repairId}")
	public String approveRepair(@PathVariable("repairId") Integer repairId) {
		Repair repair = repairService.getRepair(repairId);
		if (!repairService.isReturned(repair)){
			logger.info("Approving repair ID: " + repairId);
			repairService.approveRepair(repairId);
		}
		return "redirect:/repair/list";
	}
	
	@RequestMapping("/repair/repair/{repairId}")
	public String repairRepair(@PathVariable("repairId") Integer repairId) {
		Repair repair = repairService.getRepair(repairId);
		if (repair.getRepairDate() == null){
			repairService.repairRepair(repairId);
		}
		return "redirect:/repair/list";
	}

	@RequestMapping("/repair/delete/{repairId}")
	public String deleteClient(@PathVariable("repairId") Integer repairId) {
		logger.info("Deleting repair with ID: " + repairId);
		repairService.removeRepair(repairId);
		return "redirect:/repair/list";
	}*/
	
	/*@RequestMapping("/repair/test")
	public String addManyRecords() {
		
		for (int i = 995; i < 10000; i++){
			Repair repair = new Repair();
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername();
			repair.setId(i);
			SystemUser systemUser = userService.getUserByUsername(username);
			repairService.addRepair(repair, systemUser);
			logger.info("Adding number: " + i);
		}
		logger.info("Done!");
		return "repair_list";
	}*/

}
