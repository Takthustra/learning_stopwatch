package com.example.learning_timer.service;

/** Usersサービス処理:Service */
public interface UsersService{
    /** ユーザを登録します */
    boolean createUser(String name,String password);
    /** 特定のユーザでログインします */
    boolean loginUser(String name,String password);
    /** ユーザのパスワードを変更します */
    void updatePassword(String name,String password);
    /** ユーザを削除します */
    void deleteUser(String name,String password);
}