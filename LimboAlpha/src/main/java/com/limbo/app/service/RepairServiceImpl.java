package com.limbo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.limbo.app.dao.RepairDAO;
import com.limbo.app.domain.Repair;
 
@Service
public class RepairServiceImpl implements RepairService {
 
    @Autowired
    private RepairDAO repairDAO;
 
    @Transactional
    public void addRepair(Repair repair) {
    	repairDAO.addRepair(repair);
    }
    @Transactional
    public List<Repair> listRepair() { 
        return repairDAO.listRepair();
    }
    @Transactional
    public void removeRepair (Integer id) {
    	repairDAO.removeRepair(id);
    }
}