package com.example.learning_stopwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learning_stopwatch.entity.User;
import com.example.learning_stopwatch.form.UpdatePasswordForm;
import com.example.learning_stopwatch.form.user.LoginUserForm;
import com.example.learning_stopwatch.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
	/** DI対象 */
	@Autowired
	UserService service;
	@Autowired
	HttpSession session;

	/** フィールド */
	User user;

	/** Formの初期化 */
	@ModelAttribute
	public LoginUserForm setUpUserForm() {
		LoginUserForm form = new LoginUserForm();
		return form;
	}
	@ModelAttribute
	public UpdatePasswordForm setUpUpdatePasswordForm() {
		UpdatePasswordForm form = new UpdatePasswordForm();
		return form;
	}

	/*
	 * 
	 * ログイン処理
	 * 
	 */

	@GetMapping("login")
	public String getLogin(LoginUserForm form, Model model) {

		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		if (user != null) {
			form.setName(user.getName());
			form.setPassword(user.getPassword());

			return "user/login";
		} else {
			return "user/login";
		}
	}

	@PostMapping("login")
	public String postLogin(@Validated LoginUserForm form, BindingResult bindingResult, Model model) {
		String result = null;

		if (bindingResult.hasErrors()) {
			//ログイン画面へ戻る
			return "user/login";
		}

		//ログインできたかの判断
		boolean bool = service.loginUser(form.getName(), form.getPassword());
		if (bool) {
			return "redirect:/main/stopwatch";

		} else {
			result = "ログインできませんでした。";
		}
		model.addAttribute("result", result);
		return "user/login";
	}

	/*
	 * 
	 * ユーザ作成処理
	 */

	@GetMapping("create")
	public String getCreate(LoginUserForm form, Model model) {

		return "user/create";
	}

	@PostMapping("create")
	public String postCreate(@Validated LoginUserForm form, BindingResult bindingResult, Model model) {
		String result = null;

		if (bindingResult.hasErrors()) {
			//ユーザ作成画面へ戻る
			return "user/create";
		}

		//データベースに登録できたかの判断
		boolean bool = service.createUser(form.getName(), form.getPassword());
		if (bool) {
			result = "ユーザ名:" + form.getName() + "で登録完了しました！";

		} else {
			result = "ユーザ名:" + form.getName() + "は既に使われています。";
		}

		model.addAttribute("result", result);
		return "user/create";
	}

	/*
	 * ログアウト処理
	 */

	@GetMapping("logout")
	public String getLogout() {
		session.removeAttribute("user");
		user = null;

		return "redirect:login";
	}

	/*
	 * ユーザアカウント削除処理
	 */

	@GetMapping("delete")
	public String getDelete(Model model) {

		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}

		//deleteページで条件分岐させるためにビューへ受け渡し
		model.addAttribute("boolean", false);

		return "user/delete";
	}

	@PostMapping("delete")
	public String postDelete(Model model) {

		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}

		service.deleteUser(user);
		user = null;
		session.removeAttribute("user");

		//deleteページで条件分岐させるためにビューへ受け渡し
		model.addAttribute("boolean", true);

		return "user/delete";
	}

	/*
	 * パスワード更新処理
	 */

	@GetMapping("update_password")
	public String getUpdatePassword(UpdatePasswordForm form, Model model) {

		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}

		return "user/update_password";
	}

	@PostMapping("update_password")
	public String postUpdatePassword(@Validated UpdatePasswordForm form, BindingResult bindingResult, Model model) {
		
		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}
		
		if (bindingResult.hasErrors()) {
			//パスワード更新画面へ戻る
			return "user/update_password";
		}

		String result;
		String userName = user.getName();
		String currentPassword = user.getPassword();
		
		//formのデータを抽出
		String oldPassword = form.getOldPassword();
		String newPassword = form.getNewPassword();
		String checkPassword = form.getCheckPassword();
		
		
		if (!oldPassword.equals(currentPassword)) {

			//登録されているパスワードと現在のパスワードの照合
			result = "登録されているパスワードと入力された現在のパスワードが一致しません。";

		} else if (!newPassword.equals(form.getCheckPassword())) {

			//新しいパスワードと確認用パスワードの照合
			result = "新しいパスワードと確認用パスワードが一致しません。";

		}else if(oldPassword.equals(form.getNewPassword())) {
			
			//登録されているパスワードと新しいパスワードが同じ場合
			result = "現在のパスワードと新しいパスワードが同じです。";
			
		}else {

			//パスワード変更処理
			service.updatePassword(userName, form.getNewPassword());
			//内部的に再度ログイン処理（Sessionを更新するため）
			service.loginUser(user.getName(), form.getNewPassword());
			result = "変更しました";

		}

		model.addAttribute("result", result);
		return "user/update_password";
	}

}
