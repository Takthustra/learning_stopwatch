package com.example.learning_stopwatch.controller;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learning_stopwatch.entity.User;
import com.example.learning_stopwatch.form.StopwatchForm;
import com.example.learning_stopwatch.service.LearningTimeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("main")
public class MainController {
	/** DI対象 */
	@Autowired
	LearningTimeService service;
	@Autowired
	HttpSession session;
	
	//フィールド
	User user;
	
	/** Formの初期化 */
	@ModelAttribute
	public StopwatchForm setUpForm() {
		StopwatchForm form = new StopwatchForm();
		return form;
	}

	@GetMapping
	public String getMain(StopwatchForm form,Model model) {
		 user = (User) session.getAttribute("user");

		//ログイン時のsessionが生成されているか確認
		if (user != null) {
			//本日の学習時間データがあるか確認
			int time = service.getTodaysTime(user.getId());
			if(time != 0) {
				form.setStopTime(time);
			}
			
			return "main/top";
		} else {
			return "redirect:/user/login";
		}
		
	}
	
	@PostMapping
	public String postMain(StopwatchForm form,Model model) {
		user = (User) session.getAttribute("user");
		
		//ログイン時のsessionが生成されているか確認
		if (user != null) {
			int id = user.getId();
						
			// DBに保存
			service.setTodaysData(id,Time.valueOf(form.getTime()),form.getMemo());
			
			return "main/top";
		} else {
			return "redirect:/user/login";
		}
		
		//
		
	}
	
}
