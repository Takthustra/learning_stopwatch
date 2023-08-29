package com.example.learning_stopwatch.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.learning_stopwatch.entity.User;

/** Usersテーブル:RepositoryImpl */
public interface UserRepository extends CrudRepository<User,Integer>{
	//ユーザ登録
	@Modifying
	@Query("insert into user (name,password) values (:name,:password)")
	void createUser(@Param("name") String name,@Param("password") String password);
	
	//ユーザ重複確認
	@Query("select name from user where name = :name")
	String uniqueUser(@Param("name") String name);
	
	//ユーザ照合
	@Query("select * from user where name = :name and password = :password")
	User loginUser(@Param("name") String name,@Param("password") String password);

	//パスワード変更
	@Query("update user set password = :password where name = :name")
	void updatePassword(@Param("name") String name,@Param("password") String password);
}
