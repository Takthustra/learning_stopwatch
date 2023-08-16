package com.example.learning_timer.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.learning_timer.entity.Users;

/** Usersテーブル:RepositoryImpl */
public interface UsersRepository extends CrudRepository<Users,Integer>{
	//user登録
	@Modifying
	@Query("insert into users (name,password) values (:name,:password)")
	void saveUser(String name,String password);
}
