package com.limbo.app.dao;

import java.util.List;

import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepairDAOImpl implements RepairDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// move to util
	
	public void addRepair(Repair repair, SystemUser user) {
		//if (repair.get)
		repair.setUserId(user.getId());
		if (!repair.getBaterySerialNumber().isEmpty() && !repair.getBaterySerialNumber().equalsIgnoreCase("")){
			repair.setBattery(true);
		}
		if (!repair.getPhoneManufacturer().isEmpty() || !repair.getPhoneModel().isEmpty()) {
			repair.setPhone(true);
		}
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

	public Repair getRepair(Integer id){		
		Session session = sessionFactory.getCurrentSession();
		Repair repair = (Repair) session.load(Repair.class, id);
		return repair;
	}
	
	public void updateRepair(Repair repair){
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(repair);
		tx.commit();

	}
}