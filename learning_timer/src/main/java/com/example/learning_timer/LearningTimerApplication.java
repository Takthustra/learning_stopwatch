package com.example.learning_timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.learning_timer.entity.Users;
import com.example.learning_timer.repository.UsersRepository;

@SpringBootApplication
public class LearningTimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningTimerApplication.class, args)
				.getBean(LearningTimerApplication.class).execute();
	}

	@Autowired
	UsersRepository users_repository;

	private void execute() {
		//ユーザ登録
		createUser();
		//ログイン
		login();
	}

	private void createUser() {

		//ユーザ名,パスワードの入力
		String name = "hogehoge";
		String password = "hogehoge";

		//ユーザ名が存在しているかチェック
		String user = users_repository.uniqueUser(name);

		if (!user.equals(null)) {
			System.out.println("ユーザ名が重複しています。別のユーザ名にしてください。");
		} else {
			//登録実行
			users_repository.createUser(name, password);
			//登録確認
			System.out.println("登録したデータ:" + name + " " + password);
		}

	}

	private void login() {

		//ユーザ名,パスワードの入力
		String name = "hogehoge";
		String password = "hogehoge";

		//ユーザ名,パスワードの照合
		Users user = users_repository.login(name, password);

		if (user.equals(null)) {
			System.out.println("ログインに失敗しました。ユーザ名とパスワードを確認してください。");
		} else {
			//登録確認
			System.out.println("ログインしました。");
		}
	}

}
