package com.limbo.app.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
    @Column(name = "ROLE_ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "AUTHORITY")
    private String authority;
    
    
    @ManyToMany(mappedBy="roles")
   private List<SystemUser> users = new ArrayList<SystemUser>();
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<SystemUser> getUsers() {
		return users;
	}

	public void setUsers(List<SystemUser> users) {
		this.users = users;
	}
	
}