package com.example.learning_stopwatch.repository;

import java.sql.Time;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.learning_stopwatch.entity.Daily_Time;

/** Usersテーブル:RepositoryImpl */
public interface Daily_TimeRepository extends CrudRepository<Daily_Time,Integer>{
	//学習時間登録
	@Modifying
	@Query("insert into daily_time (user_id,learning_time) values(:userId,:time)")
	void createTodaysTime(@Param("userId") int userId,@Param("time") Time time);
	
	//学習時間更新
	@Modifying
	@Query("update daily_time set learning_time = :time where user_id = :userId and date = curdate();")
	void updateTodaysTime(@Param("userId") int userId,@Param("time") Time time);
	
	//本日の学習データの取得
	@Query("select * from daily_time where user_id = :userId and date = curdate();")
	Daily_Time readTodaysTime(@Param("userId") int userId);
	
	
}
