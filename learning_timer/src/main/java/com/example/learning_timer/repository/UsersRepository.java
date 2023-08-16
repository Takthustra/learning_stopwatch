package com.example.learning_timer.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.learning_timer.entity.Users;

/** Usersテーブル:RepositoryImpl */
public interface UsersRepository extends CrudRepository<Users,Integer>{
	
}
