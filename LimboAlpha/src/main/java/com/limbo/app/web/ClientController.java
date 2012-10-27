package com.limbo.app.web;

import java.util.Map;

import com.limbo.app.domain.Client;
import com.limbo.app.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping("/index")
    public String listClients(Map<String, Object> map) {

        map.put("client", new Client());
        map.put("clientList", clientService.listClient());

        return "contact";
    }
    
    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("contact") Client client,
            BindingResult result) {

    	clientService.addClient(client);

        return "redirect:/index";
    }

    @RequestMapping("/delete/{clientId}")
    public String deleteClient(@PathVariable("clientId") Integer clientId) {

    	clientService.removeClient(clientId);

        return "redirect:/index";
    }
}