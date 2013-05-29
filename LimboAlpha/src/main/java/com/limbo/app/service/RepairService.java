package com.limbo.app.service;

import java.util.List;

import com.limbo.app.domain.DataTablesRequest;
import com.limbo.app.domain.DataTablesResponse;
import com.limbo.app.domain.DeletedRepairs;
import com.limbo.app.domain.Repair;

public interface RepairService {

	// Add, update, delete...
    public void addRepair(Repair repair, Integer userId);    

    public void removeRepair(Integer id);
    
    public Repair getRepair(Integer id);
    
    public void updateRepair(Repair repair);
    
    public void approveRepair(Integer id);
       
    
    // Returns lists by criteria
    public DataTablesResponse<Repair> getDataTableResponse(DataTablesRequest dtReq, Boolean isReturned, Boolean isRepaired);
    
    public List<Repair> listReturnedRepair(boolean isReturned);   
    
    public List<Repair> listDoneOrNewRepairs(boolean isRepaired);
    
    public List<Repair> listRepair(); 
    
    public List<DeletedRepairs> listDeletedRepairs();
    
    
    // Miscellaneous
    public void repairRepair(Integer id);
    
    public boolean isRepaired(Repair repair);
    
    public boolean isReturned(Repair repar);
}