package com.limbo.app.dao;

import java.util.List;

import com.limbo.app.domain.DeletedRepairs;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;

public interface RepairDAO {

    public void addRepair(Repair repair, Integer userId);

    public List<Repair> listRepair();
    
    public List<Repair> listNewRepair(); 

    public void removeRepair(Integer id);
    
    public Repair getRepair(Integer id);
    
    public void updateRepair(Repair repair);
    
    public void approveRepair(Integer id);
        
    public boolean isReturned(Repair repair);
       
    public boolean isRepaired(Repair repair);
    
    public List<Repair> listReturnedRepair(boolean isReturned);
    
    public List<Repair> listDoneRepair();
    
    public List<DeletedRepairs> listDeletedRepairs();
    
    public void repairRepair(Integer id);
}