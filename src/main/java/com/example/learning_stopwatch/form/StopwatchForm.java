package com.example.learning_stopwatch.form;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Form */
@Data
@AllArgsConstructor
public class StopwatchForm {
	/** 経過時間 */
	private int stopTime;
    /** 学習時間 */
    private String time;
    /** メモ */
    private String memo;
    
    public StopwatchForm(){
    	time = "00:00:00";
    }
}
