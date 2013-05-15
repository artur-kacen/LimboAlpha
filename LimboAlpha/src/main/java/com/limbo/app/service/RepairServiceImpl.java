package com.limbo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limbo.app.dao.RepairDAO;
import com.limbo.app.domain.DataTablesRequest;
import com.limbo.app.domain.DataTablesResponse;
import com.limbo.app.domain.DeletedRepairs;
import com.limbo.app.domain.Repair;

@Service
public class RepairServiceImpl implements RepairService {

	@Autowired
	private RepairDAO repairDAO;

	@Transactional("dynamic")
	public void addRepair(Repair repair, Integer userId) {
		repairDAO.addRepair(repair, userId);
	}

	@Transactional("dynamic")
	public List<Repair> listRepair() {
		return repairDAO.listRepair();
	}
		
	@Transactional("dynamic")
	public List<Repair> listDoneOrNewRepairs(boolean isRepaired) {
		return repairDAO.listDoneOrNewRepairs(isRepaired);
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
	public boolean isRepaired(Repair repair) {
		return repairDAO.isRepaired(repair);
	}

	@Transactional("dynamic")
	public List<DeletedRepairs> listDeletedRepairs() {
		return repairDAO.listDeletedRepairs();
	}
	
	@Transactional("dynamic")
	public void repairRepair(Integer id) {
		repairDAO.repairRepair(id);		
	}

	@Transactional("dynamic")
	public DataTablesResponse<Repair> getDataTableResponse(
			DataTablesRequest dtReq, Boolean isReturned, Boolean isRepaired) {
		return repairDAO.getDataTableResponse(dtReq, isReturned, isRepaired);
	}
}