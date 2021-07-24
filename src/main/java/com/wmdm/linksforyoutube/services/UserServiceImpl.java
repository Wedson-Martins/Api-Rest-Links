package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmdm.linksforyoutube.models_for_access.User;
import com.wmdm.linksforyoutube.repositories.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User user) throws Exception {
		User userSalved = this.userRepository.save(user);
		if (userSalved != null) {
			return userSalved;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public List<User> getAllUser() throws Exception {
		List<User> users = this.userRepository.findAll();
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
			userInBase.get().setPassword(user.getPassword());
			userInBase.get().setActive(user.getActive());
			userInBase.get().setRoles(user.getRoles());
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
