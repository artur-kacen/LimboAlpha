package com.limbo.app.dao;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.limbo.app.domain.DataTablesRequest;
import com.limbo.app.domain.DataTablesResponse;
import com.limbo.app.domain.SystemUser;

public interface SystemUserDAO {

    public void addUser(SystemUser user);

    public List<SystemUser> listUser();

    public void removeUser(Integer id);
    
    public SystemUser getUser(Integer id);
    
    public void updateUser(SystemUser repair);
    
    public SystemUser getUserByUsername(String username);
    
    public List<SimpleGrantedAuthority> listUserAuthority(Integer id);
    
    public void  encryptPasswords() throws Exception;
    
    public DataTablesResponse<SystemUser> getDataTableResponse(DataTablesRequest dtReq);
}