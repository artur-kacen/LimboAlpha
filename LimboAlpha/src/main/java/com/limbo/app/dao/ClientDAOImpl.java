package com.limbo.app.dao;

import java.util.List;

import com.limbo.app.domain.Client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl implements ClientDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addClient(Client client) {
		sessionFactory.getCurrentSession().save(client);
	}

	@SuppressWarnings("unchecked")
	public List<Client> listClient() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Client").list();
	}

	public void removeClient(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Client client = (Client) session.load(Client.class, id);
		if (null != client) {
			sessionFactory.getCurrentSession().delete(client);
		}

	}
}