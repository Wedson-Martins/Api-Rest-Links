package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmdm.linksforyoutube.exceptions.UserNotFoundException;
import com.wmdm.linksforyoutube.models_for_access.Role;
import com.wmdm.linksforyoutube.repositories.RoleRepository;

import javassist.NotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) {
		role.setNameRole("ROLE_" + role.getNameRole().toUpperCase());
		Role roleSalved = this.roleRepository.save(role);
		if (roleSalved != null) {
			return roleSalved;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = this.roleRepository.findAll();
		if (roles.size() > 0) {
			return roles;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public Optional<Role> getRoleById(String nameRole) throws UserNotFoundException {
		nameRole = "ROLE_" + nameRole.toUpperCase();
		Optional<Role> role = this.roleRepository.findById(nameRole);
		if (role.isPresent()) {
			return role;
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public void removeRole(String nameRole) throws UserNotFoundException {
		System.out.println("******: " + nameRole);
		Optional<Role> roleInBase = this.getRoleById(nameRole);		
		System.out.println("#####: " + roleInBase.get().getNameRole());
		if (roleInBase.isPresent()) {
			this.roleRepository.deleteById(roleInBase.get().getNameRole());
		} else {
			throw new UserNotFoundException();
		}
	}
}
