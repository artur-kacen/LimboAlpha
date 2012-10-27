package com.limbo.app.service;

import java.util.List;
import com.limbo.app.domain.Client;

public interface ClientService {

    public void addClient(Client client);

    public List<Client> listClient();

    public void removeClient(Integer id);
}