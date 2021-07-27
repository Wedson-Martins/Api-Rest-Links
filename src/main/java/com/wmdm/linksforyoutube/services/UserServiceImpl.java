package com.wmdm.linksforyoutube.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wmdm.linksforyoutube.models_for_access.Role;
import com.wmdm.linksforyoutube.models_for_access.User;
import com.wmdm.linksforyoutube.repositories.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Override
	public User addUser(User user) throws Exception {
		User userToBePersist = new User();
		userToBePersist.setUserName(user.getUserName());
		userToBePersist.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userToBePersist.setActive(user.getActive());

		List<Role> roles = new ArrayList<>();

		for (Role r : user.getRoles()) {
			Role role = this.roleService.getRoleById(r.getNameRole()).get();
			System.out.println("!WEWEWEWEWEW");
			roles.add(role);
		}
		
		System.out.println("@@@@@@@@");
		userToBePersist.setRoles(roles);

		User userSalved = this.userRepository.save(userToBePersist);

		if (userSalved != null) {
			return userSalved;
		} else {
			throw new NotFoundException("NOT FOUND");
		}
	}

	@Override
	public List<User> getAllUser() throws Exception {
		List<User> users = this.userRepository.findAll();

		// for(User users){
		//
		// }

		if (users.size() > 0) {
			return users;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public Optional<User> getUserById(Long id) throws Exception {
		Optional<User> user = this.userRepository.findById(id);
		if (user.isPresent()) {
			return user;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public User updateUser(Long id, User user) throws Exception {
		Optional<User> userInBase = this.userRepository.findById(id);
		if (userInBase.isPresent()) {
			userInBase.get().setUserName(user.getUserName());
			// userInBase.get().setPassword(new
			// BCryptPasswordEncoder().encode(user.getPassword()));
			userInBase.get().setActive(user.getActive());

			List<Role> roles = new ArrayList<>();

			for (Role r : user.getRoles()) {
				Role role = this.roleService.getRoleById(r.getNameRole()).get();
				roles.add(role);
			}

			userInBase.get().setRoles(roles);
			return this.userRepository.save(userInBase.get());
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public void removeUser(Long id) throws Exception {
		Optional<User> userInBase = this.userRepository.findById(id);
		if (userInBase.isPresent()) {
			this.userRepository.deleteById(id);
		} else {
			new NotFoundException("NOT FOUND");
		}
	}

}
