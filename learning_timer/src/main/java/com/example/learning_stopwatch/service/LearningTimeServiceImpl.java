package com.example.learning_stopwatch.service;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;
import com.example.learning_stopwatch.repository.LearningTimeRepository;

@Service
public class LearningTimeServiceImpl implements LearningTimeService {
	/** Repository:注入 */
	@Autowired
	LearningTimeRepository repository;

	@Override
	public void setTodaysData(int userId, Time time, String memo) {
		//本日の学習記録が作成されているか判定
		Daily_Learning_Time dlt = repository.readTodaysData(userId);
		if (dlt == null) {
			//生成されていなければ作成処理
			repository.createTodaysData(userId, time, memo);
		} else {
			//生成されていれば更新処理
			repository.updateTodaysData(userId, time, memo);
		}
	}
}