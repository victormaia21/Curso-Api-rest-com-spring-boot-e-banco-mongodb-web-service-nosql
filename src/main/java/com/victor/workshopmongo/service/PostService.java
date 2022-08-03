package com.victor.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.workshopmongo.domain.Post;
import com.victor.workshopmongo.exception.ObjectNotFoundException;
import com.victor.workshopmongo.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	PostRepository repository;
	
	public List<Post>findall() {
		List<Post>list = repository.findAll();
		return list;
	}
	
	public Post findbyid(String id) {
		Optional<Post>user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Post>findbytitle(String text) {
		List<Post>list = repository.searchtitle(text);
		return list;
	}
	
}
