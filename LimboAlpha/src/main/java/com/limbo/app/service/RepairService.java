package com.limbo.app.service;

import java.util.List;

import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;

public interface RepairService {

    public void addRepair(Repair repair, SystemUser user);

    public List<Repair> listRepair();

    public void removeRepair(Integer id);
    
    public Repair getRepair(Integer id);
    
    public void updateRepair(Repair repair);
}