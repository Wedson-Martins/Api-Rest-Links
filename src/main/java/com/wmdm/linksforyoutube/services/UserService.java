package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import com.wmdm.linksforyoutube.models_for_access.User;

public interface UserService {

	User addUser(User user) throws Exception;
	List<User> getAllUser() throws Exception;
	Optional<User> getUserById(Long id) throws Exception;
	User updateUser(Long id, User user) throws Exception;
	void removeUser(Long id) throws Exception;

}
