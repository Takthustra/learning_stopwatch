package com.example.learning_stopwatch;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;
import com.example.learning_stopwatch.repository.LearningTimeRepository;

@SpringBootTest
class Daily_TimeTests {
	@Autowired
	LearningTimeRepository repository;

	@Test
	public void updateTodayTime() {
		
		repository.updateTodaysTime(1,Time.valueOf("02:30:00"));
		
		Daily_Learning_Time dt = repository.readTodaysTime(1);
		assertEquals(dt.getLearning_time(),Time.valueOf("02:30:00"));
		
	}
	
	@Test
	public void createTodayTime() {
		
		repository.createTodaysTime(2,Time.valueOf("02:00:00"));
		
		Daily_Learning_Time dt = repository.readTodaysTime(1);
		assertEquals(dt.getLearning_time(),Time.valueOf("02:00:00"));
		
	}

}
