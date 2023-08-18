package com.example.learning_timer.controller;

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

import com.example.learning_timer.form.CreateUserForm;
import com.example.learning_timer.service.UsersService;

@Controller
@RequestMapping("user")
public class UsersController {
	/** DI対象 */
	@Autowired
	UsersService service;

	@ModelAttribute
	public CreateUserForm setUpForm() {
		CreateUserForm form = new CreateUserForm();
		return form;
	}

	@GetMapping("create")
	public String showCreate(CreateUserForm form, Model model) {
		
		return "create";
	}

	@PostMapping("create")
	public String completeCreate(
			@Validated CreateUserForm form,BindingResult bindingResult, Model model
			) {	
		String result = null;

		if (bindingResult.hasErrors()) {
			//ユーザ作成画面へ戻る
			return "create";
		} else {
			//データベースに登録できたかの判断
			boolean bool = service.createUser(form.getName(), form.getPassword());
			if (bool) {
				result = "ユーザ名:" + form.getName() + "で登録完了しました！";
			} else {
				result = "ユーザ名:" + form.getName() + "は既に使われています。";
			}
		}

		model.addAttribute("result", result);
		return "create";
		
	}

}
