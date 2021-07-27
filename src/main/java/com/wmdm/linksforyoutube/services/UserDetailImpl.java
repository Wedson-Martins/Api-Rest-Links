package com.wmdm.linksforyoutube.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wmdm.linksforyoutube.models_for_access.Role;
import com.wmdm.linksforyoutube.models_for_access.User;


public class UserDetailImpl implements UserDetails {

	private String userName;
	private String password;
	private boolean isActive;
	private List<GrantedAuthority> authorities;

	public UserDetailImpl(User user) {
		System.out.println("---UserDetailImpl---");
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.isActive = user.getActive();

		for(Role r : user.getRoles()){
			System.out.println("New Roles: " + r.getAuthority());
//			this.authorities.add(r);
			String roleTemp = r.getAuthority();
			this.authorities = Arrays.stream(roleTemp.split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		}
		
		System.out.println("UserName: " + this.userName);
		System.out.println("Password: " + this.password);
		System.out.println("Active: " + this.isActive);

		// this.authorities = user.getRoles().;

		// this.authorities =
		// Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
		// .collect(Collectors.toList());
		
}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isActive;
	}
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	

}
