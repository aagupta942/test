package com.mylearning.springboot.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mylearning.springboot.bo.Posts;
import com.mylearning.springboot.bo.User;
import com.mylearning.springboot.bo.UserPrivacyInformation;
import com.mylearning.springboot.service.UserService;
import com.mylearning.springboot.utils.PostsRepository;
import com.mylearning.springboot.utils.UserNotFoundException;
import com.mylearning.springboot.utils.UserRepository;

@RestController
public class UserJpaController {

	@Autowired
	UserService service;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostsRepository postsRepository;

	@GetMapping(path = "jpa/find-all-users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "jpa/find-by-id/{id}")
	public EntityModel<User> getUserById(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		EntityModel<User> resource = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping(path = "jpa/add-user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User createdUser = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(path = "jpa/delete-by-id/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping(path = "jpa/users/{id}/posts")
	public List<Posts> getPosts(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id "+id);
		}
		return user.get().getPosts();
	}
	
	@GetMapping(path = "jpa/users/{id}/privacy")
	public UserPrivacyInformation getPrivacyInfo(@PathVariable Integer id) {
		UserPrivacyInformation userPrivacyInformation = restTemplate.getForObject("http://localhost:8080/user/"+id+"/privacy", UserPrivacyInformation.class);
		return userPrivacyInformation;
	}
	
	@PostMapping(path = "jpa/users/{id}/posts")
	public ResponseEntity<Object> addPost(@PathVariable Integer id, @Valid @RequestBody Posts posts) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("id "+id);
		}
		User user = optionalUser.get();
		posts.setUser(user);
		postsRepository.save(posts);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(posts.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
