package com.mylearning.springboot.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mylearning.springboot.bo.User;
import com.mylearning.springboot.service.UserService;
import com.mylearning.springboot.utils.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@GetMapping(path = "/find-all-users")
	public List<User> getAllUsers() {
		return service.findAllUsers();
	}

	@GetMapping(path = "/find-by-id/{id}")
	public EntityModel<User> getUserById(@PathVariable Integer id) {
		User user = service.findById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping(path = "/add-user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User createdUser = service.addUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(path = "/delete-by-id/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = service.deleteUser(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}

}
