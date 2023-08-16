package com.example.learning_timer.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	/** 識別ID */
	@Id
	private Integer id;
	/** ユーザ名 */
	private String name;
	/** パスワード */
	private String password;
	/** 作成日時 */
	private String created_at;
	/** 作成日時 */
	private String updated_at;

}
