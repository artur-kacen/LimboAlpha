package com.limbo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

import com.limbo.app.dao.RepairDAO;
import com.limbo.app.domain.DeletedRepairs;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;

@Service
public class RepairServiceImpl implements RepairService {

	@Autowired
	private RepairDAO repairDAO;

	@Transactional("dynamic")
	public void addRepair(Repair repair, SystemUser user) {
		repairDAO.addRepair(repair, user);
	}

	@Transactional("dynamic")
	public List<Repair> listRepair() {
		return repairDAO.listRepair();
	}

	@Transactional("dynamic")
	public void removeRepair(Integer id) {
		repairDAO.removeRepair(id);
	}

	@Transactional("dynamic")
	public Repair getRepair(Integer id) {
		return repairDAO.getRepair(id);
	}

	@Transactional("dynamic")
	public void updateRepair(Repair repair) {
		repairDAO.updateRepair(repair);
	}

	@Transactional("dynamic")
	public void approveRepair(Integer id) {
		repairDAO.approveRepair(id);
	}

	public boolean isReturned(Repair repair) {
		return repairDAO.isReturned(repair);
	}

	@Transactional("dynamic")
	public List<Repair> listReturnedRepair(boolean isReturned) {
		return repairDAO.listReturnedRepair(isReturned);
	}

	@Transactional("dynamic")
	public List<Repair> listDoneRepair() {
		return repairDAO.listDoneRepair();
	}

	public boolean isRepaired(Repair repair) {
		return repairDAO.isRepaired(repair);
	}

	public List<DeletedRepairs> listDeletedRepairs() {
		// TODO Auto-generated method stub
		return repairDAO.listDeletedRepairs();
	}

	public void repairRepair(Integer id) {
		// TODO Auto-generated method stub
		repairDAO.repairRepair(id);		
	}
}