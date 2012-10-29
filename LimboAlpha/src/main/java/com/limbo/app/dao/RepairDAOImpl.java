package com.limbo.app.dao;

import java.util.List;

import com.limbo.app.domain.Repair;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepairDAOImpl implements RepairDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addRepair(Repair repair) {
		sessionFactory.getCurrentSession().save(repair);
	}

	@SuppressWarnings("unchecked")
	public List<Repair> listRepair() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Repair").list();
	}

	public void removeRepair(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Repair repair = (Repair) session.load(Repair.class, id);
		if (null != repair) {
			sessionFactory.getCurrentSession().delete(repair);
		}

	}
}