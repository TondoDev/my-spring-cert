package org.tondo.bootms.rws.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tondo.bootms.rws.beans.User;
import org.tondo.bootms.rws.exception.UserNotFoundException;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/jpa/users/")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	
	@GetMapping("/jpa/users/{id}")
	public User retrieveUser( @PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return user.get();
	}
	
//	// HATEOAS implementation of GET detail
//	@GetMapping("/usersHate/{id}")
//	public EntityModel<User> retrieveUserHateoas( @PathVariable int id) {
//		User user =  service.findOne(id);
//		
//		if (user == null) {
//			throw new UserNotFoundException("id-" + id);
//		}
//		
//		EntityModel<User> model = EntityModel.of(user);
//		// we are adding links to our other resources
//		WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(
//				// points to method of current controller
//				WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()
//				);
//		
//		model.add(linkToUsers.withRel("all-users"));
//		return model;
//	}
//
//	
//	@PostMapping("/users/")
//	public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
//		User saved = service.save(user);
//		
//		// we don't want to hardcode the URI in response location header
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
//			.buildAndExpand(saved.getId()).toUri();
//		
//		return ResponseEntity.created(location).build();
//	}
//	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
}
