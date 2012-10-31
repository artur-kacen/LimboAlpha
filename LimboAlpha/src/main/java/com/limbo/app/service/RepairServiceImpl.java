package com.limbo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limbo.app.dao.RepairDAO;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;
 
@Service
public class RepairServiceImpl implements RepairService {
 
    @Autowired
    private RepairDAO repairDAO;
 
    @Transactional
    public void addRepair(Repair repair, SystemUser user) {
    	repairDAO.addRepair(repair, user);
    }
    @Transactional
    public List<Repair> listRepair() { 
        return repairDAO.listRepair();
    }
    @Transactional
    public void removeRepair (Integer id) {
    	repairDAO.removeRepair(id);
    }
    @Transactional
    public Repair getRepair(Integer id){
    	return repairDAO.getRepair(id);
    }
    @Transactional
    public void updateRepair(Repair repair){
    	repairDAO.updateRepair(repair);
    }
    @Transactional
    public void approveRepair(Integer id){
    	repairDAO.approveRepair(id);
    }
    
    @Transactional
    public boolean isReturned(Integer id){
    	return repairDAO.isReturned(id);
    }
    
    
    public boolean isReturned(Repair repar){
    	return repairDAO.isReturned(repar);
    }
}