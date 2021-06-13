package org.tondo.bootms.rws.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.tondo.bootms.rws.beans.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int idCounter = 1;
	
	static {
		users.add(create("Tonko", LocalDate.now()));
		users.add(create("Petko", LocalDate.of(1987, 5, 12)));
	}
	
	
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(genId());
		}
		
		// TODO WTF? updating existing user is not covered
		users.add(user);
		return user;
	}
	
	
	public User findOne(int id) {
		
		return users.stream()
				.filter(u -> u.getId() == id) // getId() will be unboxed
				.findFirst()
				.orElse(null);
	}
	
	public List<User> findAll() {
		return users;
	}
	
	
	public User deletedById(int id) {
		Optional<User> userToDelete = users.stream().filter(u -> u.getId() == id).findFirst();
		
		if (userToDelete.isEmpty()) {
			return null;
		}
		
		users.remove(userToDelete.get());
		return userToDelete.get();
	}
	
	private static Integer genId() {
		return idCounter++;
	}

	private static User create(String name, LocalDate birthDate) {
		User user = new User();
		
		user.setId(genId());
		user.setName(name);
		user.setBirthDate(birthDate);
		
		return user;
	}
	
	
	
}


