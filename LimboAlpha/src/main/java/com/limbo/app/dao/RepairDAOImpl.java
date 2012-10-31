package com.limbo.app.dao;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		if (repair.getBaterySerialNumber() != null && !repair.getBaterySerialNumber().isEmpty() && !repair.getBaterySerialNumber().equalsIgnoreCase("")){
			repair.setBattery(true);
		}
		if ((repair.getPhoneManufacturer() != null && !repair.getPhoneManufacturer().isEmpty()) || (repair.getPhoneModel() != null && !repair.getPhoneModel().isEmpty())) {
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
			session.delete(repair);
			session.flush();
		}

	}

	public Repair getRepair(Integer id){		
		Session session = sessionFactory.getCurrentSession();
		Repair repair = (Repair) session.load(Repair.class, id);
		return repair;
	}
	
	public void updateRepair(Repair repair){		
		Session session = sessionFactory.getCurrentSession();
		session.merge(repair);
		session.flush();
	}	
	
	public void approveRepair(Integer id){
		Repair repair = getRepair(id);
		repair.setReturned(true);
		java.util.Date today = new java.util.Date();
		Date date = new Date(today.getTime());
		repair.setReturnDate(date);
		updateRepair(repair);
	}
	
	public boolean isReturned(Integer id){
		return getRepair(id).isReturned();		
	}
	
	public boolean isReturned(Repair repair){
		return repair.isReturned();
	}
}