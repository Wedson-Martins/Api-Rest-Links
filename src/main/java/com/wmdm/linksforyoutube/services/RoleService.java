package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import com.wmdm.linksforyoutube.exceptions.OperationNotPerformedException;
import com.wmdm.linksforyoutube.exceptions.UserNotFoundException;
import com.wmdm.linksforyoutube.models_for_access.Role;

public interface RoleService {

	Role addRole(Role role) throws OperationNotPerformedException;

	List<Role> getAllRoles() throws OperationNotPerformedException;

	Optional<Role> getRoleById(String nameRole) throws UserNotFoundException, OperationNotPerformedException;

	void removeRole(String nameRole) throws UserNotFoundException, OperationNotPerformedException;
}