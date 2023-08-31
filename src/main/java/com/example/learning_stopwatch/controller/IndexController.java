package com.example.learning_stopwatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learning_stopwatch.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping()
public class IndexController {
	@Autowired
	HttpSession session;

	@GetMapping()
	public String getIndex(Model model) {
		//ログイン時のsessionが生成されているか確認
		User user = (User) session.getAttribute("user");
		
		if(user != null) {
			model.addAttribute("boolean",true);
		}
		
		return "index/index";

	}

}
