package com.example.learning_timer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.learning_timer.repository.UsersRepository;

@SpringBootTest
class LearningTimerApplicationTests {
	@Autowired
	UsersRepository repository;

	@Test
	void contextLoads() {
		// boolean bool = repository.uniqueUser("hoge");
		// System.out.println(bool);
		// assertEquals(bool,true);

	}

}
