package com.limbo.app.domain;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    
    
//    @ManyToMany(mappedBy="roles")
//    private Set<SystemUser> users = new HashSet<SystemUser>();
    
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

//	public Set<SystemUser> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<SystemUser> users) {
//		this.users = users;
//	}
}