package com.limbo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.limbo.app.dao.ClientDAO;
import com.limbo.app.domain.Client;
 
@Service
public class ClientServiceImpl implements ClientService {
 
    @Autowired
    private ClientDAO clientDAO;
 
    @Transactional
    public void addClient(Client client) {
    	clientDAO.addClient(client);
    }
 
    @Transactional
    public List<Client> listClient() { 
        return clientDAO.listClient();
    }
 
    @Transactional
    public void removeClient (Integer id) {
    	clientDAO.removeClient(id);
    }
}