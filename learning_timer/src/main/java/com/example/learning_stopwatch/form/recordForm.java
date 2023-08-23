package com.example.learning_stopwatch.form;

import java.sql.Time;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Form */
@Data
@AllArgsConstructor
public class recordForm {
    /** ユーザ名 */
    private String name;

    /** 作成日 */
    private Timestamp created_at;

    /** 総学習時間 */
    private Time totalTime;
    
}
