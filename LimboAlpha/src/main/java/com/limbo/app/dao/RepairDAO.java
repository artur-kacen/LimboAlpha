package com.limbo.app.dao;

import java.util.List;
import com.limbo.app.domain.Repair;

public interface RepairDAO {

    public void addRepair(Repair client);

    public List<Repair> listRepair();

    public void removeRepair(Integer id);
}