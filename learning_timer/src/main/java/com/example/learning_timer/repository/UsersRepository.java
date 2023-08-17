package com.example.learning_timer.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.learning_timer.entity.Users;

/** Usersテーブル:RepositoryImpl */
public interface UsersRepository extends CrudRepository<Users,Integer>{
	//ユーザ登録
	@Modifying
	@Query("insert into users (name,password) values (:name,:password)")
	void createUser(@Param("name") String name,@Param("password") String password);
	
	//ユーザ重複確認
	@Query("SELECT NAME FROM USERS WHERE NAME = :name")
	String uniqueUser(@Param("name") String name);
	
	//ログイン
	@Query("SELECT * FROM USERS WHERE NAME = :name AND PASSWORD = :password")
	Users login(@Param("name") String name,@Param("password") String password);
}
