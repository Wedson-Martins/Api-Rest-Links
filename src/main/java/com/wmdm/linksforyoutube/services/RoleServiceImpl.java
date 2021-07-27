package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmdm.linksforyoutube.exceptions.OperationNotPerformedException;
import com.wmdm.linksforyoutube.exceptions.RoleNotFoundException;
import com.wmdm.linksforyoutube.models_for_access.Role;
import com.wmdm.linksforyoutube.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role addRole(Role role) throws OperationNotPerformedException {
		role.setNameRole("ROLE_" + role.getNameRole().toUpperCase());
		Role roleSalved = this.roleRepository.save(role);
		if (roleSalved != null) {
			return roleSalved;
		} else {
			throw new OperationNotPerformedException();
		}
	}

	@Override
	public List<Role> getAllRoles() throws OperationNotPerformedException {
		List<Role> roles = this.roleRepository.findAll();
		if (roles.size() > 0) {
			return roles;
		} else {
			throw new OperationNotPerformedException();
		}
	}

	@Override
	public Optional<Role> getRoleById(String nameRole) throws RoleNotFoundException {
		nameRole = "ROLE_" + nameRole.toUpperCase();
		Optional<Role> role = this.roleRepository.findById(nameRole);
		if (role.isPresent()) {
			return role;
		} else {
			throw new RoleNotFoundException();
		}
	}

	@Override
	public void removeRole(String nameRole) throws RoleNotFoundException {
		System.out.println("******: " + nameRole);
		Optional<Role> roleInBase = this.getRoleById(nameRole);		
		System.out.println("#####: " + roleInBase.get().getNameRole());
		if (roleInBase.isPresent()) {
			this.roleRepository.deleteById(roleInBase.get().getNameRole());
		} else {
			throw new RoleNotFoundException();
		}
	}
}
