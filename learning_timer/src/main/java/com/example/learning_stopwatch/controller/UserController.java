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
/** Userコントローラ */

import com.example.learning_stopwatch.entity.User;
import com.example.learning_stopwatch.form.UserForm;
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
	public UserForm setUpForm() {
		UserForm form = new UserForm();
		return form;
	}

	/*
	 * 
	 * ログイン処理
	 * 
	 */

	@GetMapping("login")
	public String getLogin(UserForm form, Model model) {

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
	public String postLogin(@Validated UserForm form, BindingResult bindingResult, Model model) {
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
	public String getCreate(UserForm form, Model model) {

		return "user/create";
	}

	@PostMapping("create")
	public String postCreate(@Validated UserForm form, BindingResult bindingResult, Model model) {
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
		model.addAttribute("boolean",false);

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
		model.addAttribute("boolean",true);

		return "user/delete";
	}

}
