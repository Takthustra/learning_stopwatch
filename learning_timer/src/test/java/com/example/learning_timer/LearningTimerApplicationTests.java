package com.example.learning_timer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.learning_timer.repository.UsersRepository;
import com.example.learning_timer.service.UsersService;

@SpringBootTest
class LearningTimerApplicationTests {
	@Autowired
	UsersRepository repository;
	@Autowired
	UsersService service;

	@Test
	public void contextLoads() {
		 String str = repository.uniqueUser("hoge");
		 System.out.println(str);
		 assertEquals(str,"hoge");
	
	}
	@Test
	public void createUser() {
		 boolean bool1 = service.createUser("hoge", "hoge");
		assertEquals(bool1,false);
		boolean bool2 = service.createUser("dna","dna");
		assertEquals(bool2,false);
	}

}
