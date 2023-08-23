package com.example.learning_stopwatch;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;
import com.example.learning_stopwatch.repository.LearningTimeRepository;
import com.example.learning_stopwatch.service.LearningTimeService;

@SpringBootTest
class LearningTimeTests {
	@Autowired
	LearningTimeRepository repository;
	@Autowired
	LearningTimeService service;

	@Test
	public void updateTodayTime() {
		
		repository.updateTodaysData(1,Time.valueOf("02:30:00"),null);
		
		Daily_Learning_Time dt = repository.readTodaysData(1);
		assertEquals(dt.getLearning_time(),Time.valueOf("02:30:00"));
		
	}
	
	@Test
	public void createTodayTime() {
		
		repository.createTodaysData(2,Time.valueOf("02:00:00"),null);
		
		Daily_Learning_Time dt = repository.readTodaysData(1);
		assertEquals(dt.getLearning_time(),Time.valueOf("02:00:00"));
		
	}
	
	@Test
	public void setTodayTime() {
		int userId1 = 1;
		Time time1 = Time.valueOf("03:03:03");
		
		service.setTodaysData(userId1, time1,"今日は3時間がんばった");
		Daily_Learning_Time dt1 = repository.readTodaysData(userId1);
		assertEquals(dt1.getLearning_time(),Time.valueOf("03:03:03"));
		
		int userId2 = 2;
		Time time2 = Time.valueOf("01:02:03");
		
		service.setTodaysData(userId2, time2,"今日は1時間集中した");
		Daily_Learning_Time dt2 = repository.readTodaysData(userId2);
		assertEquals(dt2.getLearning_time(),Time.valueOf("01:02:03"));
	}
	
	@Test
	public void getTodayTime() {
		int userId = 1;
		int time = service.getTodaysTime(userId);
		assertEquals(time,5000);
	}	
	
	@Test
	public void readTotalTime() {
		int userId = 1;
		Time time = repository.readTotalTime(userId);
		assertEquals(time.toString(),"03:03:19");
		
	}

	@Test
	public void getTotalTime() {
		int userId1 = 1;
		Time time1 = service.getTotalTime(userId1);
		assertEquals(time1.toString(),"03:03:19");

		int userId2 = 100;
		Time time2 = service.getTotalTime(userId2);
		assertEquals(time2.toString(),"00:00:00");
		
	}
	
	@Test
	public void readAllData() {
		int userId = 1;
		List<Daily_Learning_Time> times = repository.readAllData(userId);
		Daily_Learning_Time time1 = times.get(0);
		Daily_Learning_Time time2 = times.get(1);
		assertEquals(time1.getLearning_time().toString(),"03:03:03");
		assertEquals(time2.getLearning_time().toString(),"00:00:16");
	}
	
	@Test
	public void getAllData() {
		int userId = 1;
		List<Daily_Learning_Time> times = service.getAllData(userId);
		Daily_Learning_Time time1 = times.get(0);
		Daily_Learning_Time time2 = times.get(1);
		assertEquals(time1.getLearning_time().toString(),"03:03:03");
		assertEquals(time2.getLearning_time().toString(),"00:00:16");
	}

}
