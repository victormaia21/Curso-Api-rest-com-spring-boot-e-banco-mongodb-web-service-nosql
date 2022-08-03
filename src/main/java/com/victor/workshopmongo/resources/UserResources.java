package com.victor.workshopmongo.resources;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.workshopmongo.domain.Post;
import com.victor.workshopmongo.domain.User;
import com.victor.workshopmongo.dto.UserDto;
import com.victor.workshopmongo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	
	@Autowired
	UserService service;

	@GetMapping
	public ResponseEntity<List<UserDto>>findall() {
		List<User>list = service.findall();
		List<UserDto>listdto = list.stream().map(x  -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listdto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto>findbyid(@PathVariable String id) {
		User user = service.findbyid(id);
		return ResponseEntity.ok().body(new UserDto(user));
	}
	
	@PostMapping
	public ResponseEntity<Void>insert(@RequestBody UserDto u) {
		User user = service.fromdto(u);
		user = service.insert(user);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void>update(@RequestBody UserDto u,@PathVariable String id) {
		User user = service.fromdto(u);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
		
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<Set<Post>>findpost(@PathVariable String id) {
		User u = service.findbyid(id);
		return ResponseEntity.ok().body(u.getPosts());
	}
	

	
	
}
