package com.victor.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.victor.workshopmongo.domain.Post;
import com.victor.workshopmongo.domain.User;
import com.victor.workshopmongo.dto.AuthorDto;
import com.victor.workshopmongo.dto.CommentDto;
import com.victor.workshopmongo.repositories.PostRepository;
import com.victor.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	UserRepository repuse;
	
	@Autowired
	PostRepository reppost;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		repuse.deleteAll();
		reppost.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		repuse.saveAll(Arrays.asList(maria,alex,bob));
		
		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para são paulo,abraços",new AuthorDto(maria));
		Post p2 = new Post(null, sdf.parse("21/03/2018"), "boa dia", "Vou viajar para o acre,abraços",new AuthorDto(maria));
		
		
		CommentDto c1 = new CommentDto("Tenha um otimo dia", sdf.parse("31/03/2020"), new AuthorDto(alex));
		CommentDto c2 = new CommentDto("Uma boa viagem", sdf.parse("31/03/2020"), new AuthorDto(alex));
		CommentDto c3 = new CommentDto("Uma boa Amigo", sdf.parse("31/03/2020"), new AuthorDto(maria));
		
		p1.getComments().addAll(Arrays.asList(c1,c2));
		p2.getComments().add(c3);
		
		reppost.saveAll(Arrays.asList(p1,p2));
		
		maria.getPosts().addAll(Arrays.asList(p1,p2));
		repuse.save(maria);
	}

}
