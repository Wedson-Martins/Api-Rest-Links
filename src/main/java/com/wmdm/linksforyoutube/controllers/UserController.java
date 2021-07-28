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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wmdm.linksforyoutube.models_for_access.User;
import com.wmdm.linksforyoutube.services.UserService;

//import io.swagger.annotations.Api;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/users")
//@SecurityRequirement(name = "wmdmAPI")
//@Api( tags = "Clients")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/adduser")
	public ResponseEntity<?> add(@RequestBody User user) {
		System.out.println("!!!!!!!!!!!!!!!!!!!");
		try {
			User userSalved = this.userService.addUser(user);
			return new ResponseEntity<User>(userSalved, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("%%%%%%%%%%%%%%%%%%%");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getallusers" , produces = "application/json")
	public ResponseEntity<?> getAll() {
		try {
			List<User> users = this.userService.getAllUser();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
		try {
			Optional<User> userInBase = this.userService.getUserById(id);
			return new ResponseEntity<User>(userInBase.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody User user) {
		try {
			User userInBase = this.userService.updateUser(id, user);
			return new ResponseEntity<User>(userInBase, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		try {
			this.userService.removeUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
