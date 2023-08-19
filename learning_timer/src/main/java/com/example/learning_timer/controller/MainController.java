package com.example.learning_timer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learning_timer.form.UserForm;
import com.example.learning_timer.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("main")
public class MainController {
	/** DI対象 */
	@Autowired
	UsersService service;
	@Autowired
	HttpSession session;

	@GetMapping
	public String getMain(UserForm form, Model model) {
		String name = (String) session.getAttribute("name");

		//ログイン時のsessionが生成されているか確認
		if (name != null) {
			return "main";
		} else {
			return "redirect:/user/login";
		}

	}
}