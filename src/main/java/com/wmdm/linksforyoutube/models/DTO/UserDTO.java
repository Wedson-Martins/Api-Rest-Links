package com.wmdm.linksforyoutube.models.DTO;

import com.wmdm.linksforyoutube.models_for_access.User;

public class UserDTO {

	private String userName;
	private String password;
	private String role;
	private boolean active;
	
	
	public User getUser(){
		
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setActive(active);
		
		
		
		
		
		return user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
