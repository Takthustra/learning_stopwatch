package com.example.learning_stopwatch.service;

import com.example.learning_stopwatch.entity.User;

/** Usersサービス処理:Service */
public interface UserService{
    /** ユーザを登録します */
    boolean createUser(String name,String password);
    /** 特定のユーザでログインします */
    boolean loginUser(String name,String password);
    /** ユーザのパスワードを変更します */
    void updatePassword(String name,String password);
    /** ユーザを削除します */
    void deleteUser(User user);
    /** ユーザのデータをクリアします */
    void clearUserData(User user);
}