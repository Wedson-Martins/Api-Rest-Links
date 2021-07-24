package com.wmdm.linksforyoutube.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wmdm.linksforyoutube.exceptions.OperationNotPerformedException;
import com.wmdm.linksforyoutube.exceptions.UserNotFoundException;
import com.wmdm.linksforyoutube.models_for_access.Role;
import com.wmdm.linksforyoutube.services.RoleService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/addrole")
	public ResponseEntity<?> add(@RequestBody Role role) {
		try {
			Role roleSalved = this.roleService.addRole(role);
			return new ResponseEntity<Role>(roleSalved, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getallrole")
	public ResponseEntity<?> getAll() {
		try {
			List<Role> roles = this.roleService.getAllRoles();
			return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getrolebyid/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id") String nameRole) {
		try {
			Optional<Role> roleInBase = this.roleService.getRoleById(nameRole);
			return new ResponseEntity<Role>(roleInBase.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleterole/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String nameRole) {

		try {
			this.roleService.removeRole(nameRole);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (OperationNotPerformedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
