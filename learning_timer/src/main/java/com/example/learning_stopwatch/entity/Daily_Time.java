package com.example.learning_stopwatch.entity;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class Daily_Time {
		/** 識別ID */
		@Id
		private Integer id;
		/** ユーザID */
		private String user_id;
		/** 日付 */
		private Date date;
		/** 学習時間 */
		private Time learning_time;
		/** メモ */
		private String Memo;	
		/** パスワード */
		private String password;
		/** 作成日時 */
		private Timestamp created_at;
		/** 更新日時 */
		private Timestamp updated_at;
	
	}
