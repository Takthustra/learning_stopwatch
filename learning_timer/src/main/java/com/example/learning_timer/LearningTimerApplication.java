package com.example.learning_timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.learning_timer.entity.Users;
import com.example.learning_timer.repository.UsersRepository;

@SpringBootApplication
public class LearningTimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningTimerApplication.class, args);
	}
}