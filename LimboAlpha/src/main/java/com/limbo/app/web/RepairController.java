package com.limbo.app.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;
import com.limbo.app.pdf.RepairPDFGeneration;
import com.limbo.app.service.RepairService;
import com.limbo.app.service.SystemUserService;

@Controller
public class RepairController {

	private static final Logger logger = LoggerFactory
			.getLogger(ClientController.class);

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
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
		logger.info("User name : " + name);
		map.put("repair", new Repair());
		return "repair_add";
	}

	
	@RequestMapping(value = "/repair/add", method = RequestMethod.POST)
	public String doAddRepair(@ModelAttribute("repair") Repair repair, BindingResult result) {

		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = user.getUsername();
			SystemUser systemUser = userService.getUserByUsername(username);
			logger.info("amount = " + repair.getPaymentAmount());
			repairService.addRepair(repair, systemUser);
		}
		return "redirect:/repair/list";

	}
	
	@RequestMapping("/repair/update/{repairId}")
	public String updateRepair(Map<String, Object> map,
			@PathVariable("repairId") Integer repairId) {
		logger.info("Showing repair ID: " + repairId);
		map.put("repairId", repairId);
		map.put("repair", repairService.getRepair(repairId));
		return "repair_update";
	}

	@RequestMapping(value = "/repair/update/add", method = RequestMethod.POST)
	public String doUpdateRepair(
			@ModelAttribute("repair") Repair repair, BindingResult result) {

		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal() instanceof User) {
			//User user = (User) SecurityContextHolder.getContext()
			//		.getAuthentication().getPrincipal();
			// Create logging who did last change
			//String username = user.getUsername();
			//SystemUser systemUser = userService.getUserByUsername(username);
			repairService.updateRepair(repair);
		}
		return "redirect:/repair/list";

	}

	@RequestMapping("/repair/list")
	public String listRepair(Map<String, Object> map) {
		return "redirect:/repair/list/all"; 
	}
	
	@RequestMapping("/repair/list/all")
	public String listAllRepair(Map<String, Object> map) {
		map.put("repairList", repairService.listRepair());
		map.put("repairService", repairService);
		return "repair_list";
	}
	
	@RequestMapping("/repair/list/repaired")
	public String listRepairedRepair(Map<String, Object> map) {
		map.put("repairList", repairService.listDoneRepair());
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
	}
	
	
	@RequestMapping("/repair/getpdf/{repairId}")
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
		return "repair_list";
	}
	
	@RequestMapping("/repair/approve/{repairId}")
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
	}
	
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
