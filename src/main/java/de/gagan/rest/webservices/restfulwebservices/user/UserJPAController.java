package de.gagan.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import de.gagan.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	@GetMapping("/jpa/users")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<Optional<User>> getUser(@PathVariable Integer id) {
		Optional<User> user = userRepo.findById(id);
		if (Optional.empty().equals(user))
			throw new UserNotFoundException("User not found " + id);
		
		EntityModel<Optional<User>> model = EntityModel.of(user);
		
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllUsers());
		
		model.add(linkBuilder.withRel("all-users"));
		
		return model;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User savedUser = userRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepo.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getPostsByUser(@PathVariable Integer id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found " + id);
		}
		return optionalUser.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> optionalUser = userRepo.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found " + id);
		}
		post.setUser(optionalUser.get());
		postRepo.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
