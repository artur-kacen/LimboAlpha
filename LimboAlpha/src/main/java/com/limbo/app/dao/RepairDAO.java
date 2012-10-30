package com.limbo.app.dao;

import java.util.List;
import com.limbo.app.domain.Repair;
import com.limbo.app.domain.SystemUser;

public interface RepairDAO {

    public void addRepair(Repair repair, SystemUser user);

    public List<Repair> listRepair();

    public void removeRepair(Integer id);
    
    public Repair getRepair(Integer id);
    
    public void updateRepair(Repair repair);
}