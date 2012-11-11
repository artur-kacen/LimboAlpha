package com.limbo.app.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.limbo.app.domain.SystemUser;

public interface SystemUserService {

    public void addUser(SystemUser user);

    public List<SystemUser> listUser();

    public void removeUser(Integer id);
    
    public SystemUser getUser(Integer id);
    
    public void updateUser(SystemUser user);
    
    public SystemUser getUserByUsername(String username);
    
    public List<SimpleGrantedAuthority> listUserAuthority(Integer id);
    
    public void  encryptPasswords() throws Exception;
}