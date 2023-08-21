package com.example.learning_stopwatch.service;

import java.sql.Time;

/** Usersサービス処理:Service */
public interface LearningTimeService{
    /** 学習時間の登録・更新 */
    void setTodaysData(int userId,Time time,String memo);
    
}