package com.example.learning_stopwatch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.learning_stopwatch.repository.UserRepository;
import com.example.learning_stopwatch.service.UserService;

@SpringBootTest
class UserTests {
	@Autowired
	UserRepository repository;
	@Autowired
	UserService service;

	@Test
	public void uniqueUser() {
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
	@Test
	public void loginUser() {
		boolean bool1 = service.loginUser("hoge", "hoge");
		assertEquals(bool1,true);
		boolean bool2 = service.loginUser("hoge","h");
		assertEquals(bool2,false);
	}

}
