package com.limbo.app.web;

import java.util.Map;

import com.limbo.app.domain.Client;
import com.limbo.app.service.ClientService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	@Autowired
	private ClientService clientService;	
	
	@RequestMapping("/clients")
	public String clients(Map<String, Object> map) {
		map.put("client", new Client());
		map.put("clientList", clientService.listClient());
		return "clients";
	}

	@RequestMapping(value = "/clients/add", method = RequestMethod.POST)
	public String addClient(@ModelAttribute("contact") Client client,
			BindingResult result) {

		clientService.addClient(client);

		return "redirect:/clients";
	}

	@RequestMapping("/clients/delete/{clientId}")
	public String deleteClient(@PathVariable("clientId") Integer clientId) {
		logger.info("Client ID: " + clientId);
		clientService.removeClient(clientId);

		return "redirect:/clients";
	}
}