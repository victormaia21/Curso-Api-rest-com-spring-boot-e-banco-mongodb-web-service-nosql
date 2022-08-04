package com.victor.workshopmongo.resources;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victor.workshopmongo.domain.Post;
import com.victor.workshopmongo.resources.util.URL;
import com.victor.workshopmongo.service.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResources {
	
	@Autowired
	PostService service;

	@GetMapping
	public ResponseEntity<List<Post>>findall() {
		List<Post>list = service.findall();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post>findbyid(@PathVariable String id) {
		Post user = service.findbyid(id);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping(value = "searchtitle")
	public ResponseEntity<List<Post>>findbytitle(@RequestParam(value = "text",defaultValue = "") String text) {
		text = URL.decodeparam(text);
		List<Post>list = service.findbytitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "fullsearch")
	public ResponseEntity<List<Post>>fullsearch(
			@RequestParam(value = "text",defaultValue = "")String text,
			@RequestParam(value = "mindate",defaultValue = "")String minDate,
			@RequestParam(value = "maxdate",defaultValue = "")String maxDate) {
		text = URL.decodeparam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post>list = service.fullsearch(text, min, max);
		return ResponseEntity.ok().body(list);
		
	}
			
			
	
	
	
		
	
	
	
	
	
	
	
}
