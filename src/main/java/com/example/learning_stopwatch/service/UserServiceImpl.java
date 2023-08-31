package com.example.learning_stopwatch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learning_stopwatch.entity.User;
import com.example.learning_stopwatch.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
    /** Repository:注入 */
    @Autowired
    UserRepository repository;
	@Autowired
	HttpSession session;
    
    @Override
    public boolean createUser(String name,String password){
        //ユーザの重複チェック
        String str = repository.uniqueUser(name);
        if(str == null){
            repository.createUser(name, password);
            
            //作成と同時にセッションにUser情報を保存
            loginUser(name,password);
            
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean loginUser(String name,String password){
        //ユーザ名とパスワードで照合できるかチェック
    	User user = repository.loginUser(name, password);
    	if(user != null) {
    		
    		//ゲストアカウントならデータをクリア
    		if(user.getName().equals("guest")) {
    			repository.deleteUserData(user.getId());
    		}
    		
    		//照合出来たらセッションにUser情報を保存
    		session.setAttribute("user",user);
    		return true;
    	}else {
    		return false;
    	}

    }

    @Override
    public void updatePassword(String name,String password){
    	repository.updatePassword(name,password);
    }
    
    
    @Override
    public void deleteUser(User user) {
        repository.delete(user);
        
    }
    
    @Override
    public void clearUserData(User user) {
        repository.deleteUserData(user.getId());
    }

}