package com.wmdm.linksforyoutube.models_for_access;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	private String nameRole;
	
	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	@Override
	public String getAuthority() {
		return this.nameRole;
	}
}
