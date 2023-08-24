package com.example.learning_stopwatch.form;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Form */
@Data
@AllArgsConstructor
public class RecordForm {
    /** ユーザ名 */
    private String name;

    /** 作成日 */
    private Timestamp created_at;

    /** 総学習時間 */
    private Time totalTime;
    
    /** 全学習記録 */
    private List<Daily_Learning_Time> dlts;
}