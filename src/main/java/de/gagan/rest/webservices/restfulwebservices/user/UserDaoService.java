package de.gagan.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static Integer usersCount = 3;
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public Optional<User> findUser(int id) {
		return users.stream().filter(u->u.getId()==id).findFirst();
	}

	public User deleteUser(Integer id) {
		Iterator<User> userIterator = users.iterator();
		while(userIterator.hasNext()) {
			User u = userIterator.next();
			if (id == u.getId()) {
				userIterator.remove();
				return u;
			}
		}
		return null;
	}
}
