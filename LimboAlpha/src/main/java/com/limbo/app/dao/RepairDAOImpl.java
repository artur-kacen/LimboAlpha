package com.limbo.app.dao;

import java.sql.Date;
import java.util.List;

import com.limbo.app.domain.DeletedRepairs;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepairDAOImpl extends HibernateTemplate implements RepairDAO {

	
	@Autowired 
    public RepairDAOImpl(@Qualifier("dynamicSessionFactory") SessionFactory sessionFactory) { 
     super(sessionFactory); 
    }

	// move to util
	private Repair modifyRepair(Repair repair){
		if (repair.getBaterySerialNumber() != null && !repair.getBaterySerialNumber().isEmpty()){
			repair.setBattery(true);
		} else {
			repair.setBattery(false);
		}
		if (repair.getPhoneManufacturer() != null && !repair.getPhoneManufacturer().isEmpty() ) {
			repair.setPhone(true);
		} else {
			repair.setPhone(false);
		}
		if (repair.getWarrantyPeriod() != null && repair.getWarrantyPeriod() > 0) {
			repair.setWarranty(true);
		} else {
			repair.setWarranty(false);
		}
		if (repair.getReceiptDate() == null){
			repair.setReceiptDate(getCurrentDat());
		}
		
		return repair;
	}
	public void addRepair(Repair repair, Integer userId) {		
		repair.setUserId(userId);
		repair = modifyRepair(repair);
		this.getSession().save(repair);
	}

	@SuppressWarnings("unchecked")
	public List<Repair> listRepair() {
		Session session = this.getSession();
		return session.createQuery("from Repair").list();
	}

	public void removeRepair(Integer id) {
		Session session = this.getSession();
		Repair repair = (Repair) session.load(Repair.class, id);
		if (null != repair) {
			DeletedRepairs delete = new DeletedRepairs();
			BeanUtils.copyProperties(repair, delete);
			delete.setRepairId(repair.getId());
			session.save(delete);
			session.delete(repair);
			session.flush();
		}

	}

	public Repair getRepair(Integer id){		
		Session session = this.getSession();
		Repair repair = (Repair) session.load(Repair.class, id);
		return repair;
	}
	
	public void updateRepair(Repair repair){		
		Session session = this.getSession();
		repair = modifyRepair(repair);
		session.merge(repair);
		session.flush();
	}	
	
	public void approveRepair(Integer id){
		Repair repair = getRepair(id);
		repair.setReturned(true);
		if (repair.getRepairDate() == null){
			repair.setRepairDate(getCurrentDat());
		}
		repair.setReturnDate(getCurrentDat());
		updateRepair(repair);
	}
		
	public boolean isReturned(Repair repair){
		return repair.isReturned();
	}

	@SuppressWarnings("unchecked")
	public List<Repair> listReturnedRepair(boolean isReturned) {
		Session session = this.getSession();
		Query query =  session.createQuery("from Repair where returned = :returned");
		query.setParameter("returned", isReturned);
		return query.list();
	}	

	@SuppressWarnings("unchecked")
	public List<Repair> listDoneRepair() {
		Session session = this.getSession();
		Query query =  session.createQuery("from Repair where repairDate is not null and returned = :returned");
		query.setParameter("returned", false);
		return query.list();
	}

	public boolean isRepaired(Repair repair) {
		if (repair.getRepairDate() != null){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<DeletedRepairs> listDeletedRepairs() {
		Session session = this.getSession();
		return session.createQuery("from DeletedRepairs").list();
	}

	public void repairRepair(Integer id) {		
		Repair repair = getRepair(id);
		if (repair.getRepairDate() == null){
			repair.setRepairDate(getCurrentDat());
		}
		updateRepair(repair);
		
	}
	
	private Date getCurrentDat(){
		java.util.Date today = new java.util.Date();
		Date date = new Date(today.getTime());
		return date;
	}
}