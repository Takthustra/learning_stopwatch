package com.example.learning_stopwatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class LearningStopwatchApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(LearningStopwatchApplication.class, args);
	}
	
	// ここから追加
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(LearningStopwatchApplication.class);
	}
	// ここまで追加

}