package com.limbo.app.dao;

import java.util.List;


import com.limbo.app.domain.SystemUser;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SystemUserDAOImpl implements SystemUserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	public void addUser(SystemUser user) {
		//if (repair.get)
		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<SystemUser> listUser() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from SystemUser").list();
	}

	public void removeUser(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		SystemUser user = (SystemUser) session.load(SystemUser.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);
		}

	}

	public SystemUser getUser(Integer id){		
		Session session = sessionFactory.getCurrentSession();
		SystemUser user = (SystemUser) session.load(SystemUser.class, id);
		return user;
	}
	
	public void updateUser(SystemUser user){
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}
	
	public SystemUser getUserByUsername(String username){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from SystemUser where username = :username");
		query.setParameter("username", username);
		SystemUser user = (SystemUser)query.list().get(0);
		return user;
		
	}
}