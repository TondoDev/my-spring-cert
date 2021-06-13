package org.tondo.bootms.rws.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tondo.bootms.rws.beans.User;
import org.tondo.bootms.rws.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users/")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	public User retrieveUser( @PathVariable int id) {
		User user =  service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return user;
	}
	
	// HATEOAS implementation of GET detail
	@GetMapping("/usersHate/{id}")
	public EntityModel<User> retrieveUserHateoas( @PathVariable int id) {
		User user =  service.findOne(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		
		EntityModel<User> model = EntityModel.of(user);
		// we are adding links to our other resources
		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(
				// points to method of current controller
				WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()
				);
		
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}

	
	@PostMapping("/users/")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
		User saved = service.save(user);
		
		// we don't want to hardcode the URI in response location header
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
			.buildAndExpand(saved.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deletedById(id);
		
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}
}
