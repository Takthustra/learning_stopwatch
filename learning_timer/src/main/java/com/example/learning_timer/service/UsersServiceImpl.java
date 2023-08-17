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
    public String createUser(String name,String password){
        //ユーザの重複チェック
        String str = repository.uniqueUser(name);
        if(str.equals(null)){
            repository.createUser(name, password);
            return "ユーザ名:" + name +"で作成しました";
        }else{
            return "そのユーザ名は既に存在します";
        }

    }

    @Override
    public Users loginUser(String name,String password){
        return null;

    }

    @Override
    public void updatePassword(String name,String password){

    }

    @Override
    public void deleteUser(String name, String password) {
        
    }

}