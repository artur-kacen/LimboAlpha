package com.limbo.app.dao;

import java.util.List;
import com.limbo.app.domain.Client;

public interface ClientDAO {

    public void addClient(Client client);

    public List<Client> listClient();

    public void removeClient(Integer id);
}