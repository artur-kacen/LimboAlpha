package com.limbo.app.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(using = JsonSystemUserSerializer.class)
@Entity
@Table(name = "users")
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class SystemUser {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", unique = true, nullable = false)
    private String password;

	@Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "SURNAME")
    private String surname;
    
    @Column(name = "PK_CODE")
    private String pkCode;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "TELEPHONE")
    private String telephone;
    
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="user_roles", 
                joinColumns={@JoinColumn(name="USER_ID", nullable = false)}, 
                inverseJoinColumns={@JoinColumn(name="ROLE_ID", nullable = false)})
    private List<Role> roles = new ArrayList<Role>();  
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPkCode() {
		return pkCode;
	}

	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}