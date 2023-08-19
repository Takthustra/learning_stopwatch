package com.example.learning_timer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_timer.entity.Users;
import com.example.learning_timer.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{
    /** Repository:注入 */
    @Autowired
    UsersRepository repository;
    
    @Override
    public boolean createUser(String name,String password){
        //ユーザの重複チェック
        String str = repository.uniqueUser(name);
        if(str == null){
            repository.createUser(name, password);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean loginUser(String name,String password){
        //ユーザ名とパスワードで照合できるかチェック
    	Users user = repository.loginUser(name, password);
    	if(user != null) {
    		return true;
    	}else {
    		return false;
    	}

    }

    @Override
    public void updatePassword(String name,String password){

    }

    @Override
    public void deleteUser(String name, String password) {
        
    }

}