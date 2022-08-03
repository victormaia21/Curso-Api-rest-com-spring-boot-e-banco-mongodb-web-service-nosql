package com.victor.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.workshopmongo.domain.User;
import com.victor.workshopmongo.dto.UserDto;
import com.victor.workshopmongo.exception.ObjectNotFoundException;
import com.victor.workshopmongo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<User>findall() {
		List<User>list = repository.findAll();
		return list;
	}
	
	public User findbyid(String id) {
		Optional<User>user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public User insert(User user) {
		return user = repository.insert(user);
	}
	
	public void delete(String id) {
		findbyid(id);
		repository.deleteById(id);
	}
	
	public User update(User user) {
		Optional<User>entity = repository.findById(user.getId());
		updatedata(entity,user);
		return repository.save(entity.get());
	}

	private void updatedata(Optional<User> entity, User user) {
		entity.get().setName(user.getEmail());
		entity.get().setEmail(user.getEmail());
		
	}

	public User fromdto(UserDto u) {
		return new User(u.getId(), u.getName(), u.getEmail());
	}
	
	
	
	
}
