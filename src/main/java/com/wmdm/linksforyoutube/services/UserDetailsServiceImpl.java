package com.wmdm.linksforyoutube.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wmdm.linksforyoutube.models_for_access.User;
import com.wmdm.linksforyoutube.repositories.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		System.out.println("---------------------------------------------- : " + userName);
//		Optional<User> user = this.userRepository.findById(20L);
		
		Optional<User> user = this.userRepository.findByUserName(userName);
		
		System.out.println("Wedson");
		
		System.out.println("Roles: " + user.get().getRoles());
		
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not Found."));
		
		System.out.println("---UserDetailsServiceImpl---");
		
		UserDetailImpl udi = new UserDetailImpl(user.get());
			
		return udi;
//		return user.map(UserDetailImpl::new).get();
	}
}
