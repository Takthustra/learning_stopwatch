package com.example.learning_stopwatch.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;
import com.example.learning_stopwatch.repository.LearningTimeRepository;

@Component
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

	@Override
	public int getTodaysTime(int userId) {
		Daily_Learning_Time dlt = repository.readTodaysData(userId);
		if (dlt == null) {
			//生成されていなければ0を返す
			return 0;
		} else {
			//生成されていれば学習時間をミリ秒単位で返す
			Time tmp = dlt.getLearning_time();
			String str = tmp.toString();
			String[] strs = str.split(":");
			int time = 0;

			for (int i = 0; i <= 2; i++) {
				if (i == 0) {
					time += Integer.parseInt(strs[0]) * 60 * 60;
				} else if (i == 1) {
					time += Integer.parseInt(strs[1]) * 60;
				} else if (i == 2) {
					time += Integer.parseInt(strs[2]);
					time = time * 1000;
				}
			}

			return time;
		}

	}

	@Override
	public String getTodaysMemo(int userId) {
		Daily_Learning_Time dlt = repository.readTodaysData(userId);
		if (dlt == null) {
			return null;
		} else {
			return dlt.getMemo();
		}
	}

	@Override
	public Time getTotalTime(int userId) {
		Time time = repository.readTotalTime(userId);
		//学習時間が存在するか判定
		if (time == null) {
			return Time.valueOf("00:00:00");
		}
		return time;
	}

	@Override
	public List<Daily_Learning_Time> getAllData(int userId) {
		List<Daily_Learning_Time> list = repository.readAllData(userId);
		
		return list;
	}
}