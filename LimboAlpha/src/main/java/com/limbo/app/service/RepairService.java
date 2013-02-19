package com.limbo.app.service;

import java.util.List;

import com.limbo.app.domain.DeletedRepairs;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;

public interface RepairService {

    public void addRepair(Repair repair, Integer userId);

    public List<Repair> listRepair();

    public void removeRepair(Integer id);
    
    public Repair getRepair(Integer id);
    
    public void updateRepair(Repair repair);
    
    public void approveRepair(Integer id);
       
    public boolean isReturned(Repair repar);
    
    public List<Repair> listReturnedRepair(boolean isReturned);
    
    public List<Repair> listDoneRepair();
    
    public boolean isRepaired(Repair repair);
    
    public List<DeletedRepairs> listDeletedRepairs();
    
    public void repairRepair(Integer id);
}