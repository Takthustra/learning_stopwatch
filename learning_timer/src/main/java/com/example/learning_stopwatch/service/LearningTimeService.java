package com.example.learning_stopwatch.service;

import java.sql.Time;
import java.util.List;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;

/** Usersサービス処理:Service */
public interface LearningTimeService{
    /** 学習時間の登録・更新 */
    void setTodaysData(int userId,Time time,String memo);
    
    /** 本日の学習時間の取得 */
    int getTodaysTime(int userId);
    
    /** 本日のメモの取得 */
    String getTodaysMemo(int userId);
    
    /** 指定したユーザの総学習時間の取得 */
    Time getTotalTime(int userId);
    
    /** 指定したユーザの全学習記録を取得 */
    List<Daily_Learning_Time> getAllData(int userId);
}