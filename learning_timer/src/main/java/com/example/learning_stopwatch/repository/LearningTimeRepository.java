package com.example.learning_stopwatch.repository;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;

/** Usersテーブル:RepositoryImpl */
public interface LearningTimeRepository extends CrudRepository<Daily_Learning_Time,Integer>{
	//学習時間登録
	@Modifying
	@Query("insert into daily_learning_time (user_id,learning_time,memo) values(:userId,:time,:memo)")
	void createTodaysData(@Param("userId") int userId,@Param("time") Time time,@Param("memo") String memo);
	
	//学習時間更新
	@Modifying
	@Query("update daily_learning_time set learning_time = :time , memo = :memo where user_id = :userId and date = curdate();")
	void updateTodaysData(@Param("userId") int userId,@Param("time") Time time,@Param("memo") String memo);
	
	//本日の学習データの取得
	@Query("select * from daily_learning_time where user_id = :userId and date = curdate();")
	Daily_Learning_Time readTodaysData(@Param("userId") int userId);
	
	//指定したユーザの総学習時間の取得
	@Query("select sec_to_time(sum(time_to_sec(learning_time))) as total_learning_time from daily_learning_time where user_id = :userId;")
	Time readTotalTime(@Param("userId") int userId);
	
	//指定したユーザの全学習記録を取得
	@Query("select * from daily_learning_time where user_id = 1;")
	List<Daily_Learning_Time> readAllData(@Param("userId") int userId);
	
}
