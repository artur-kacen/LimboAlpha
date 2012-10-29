package com.limbo.app.service;

import java.util.List;
import com.limbo.app.domain.Repair;

public interface RepairService {

    public void addRepair(Repair repair);

    public List<Repair> listRepair();

    public void removeRepair(Integer id);
}