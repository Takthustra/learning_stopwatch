package com.example.learning_stopwatch.controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.learning_stopwatch.entity.Daily_Learning_Time;
import com.example.learning_stopwatch.entity.User;
import com.example.learning_stopwatch.form.RecordForm;
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

	@GetMapping("stopwatch")
	public String getMain(StopwatchForm form, Model model) {

		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		
		if (user != null) {
			//本日の学習時間データ取得
			int time = service.getTodaysTime(user.getId());
			form.setStopTime(time);
			
			//本日の学習メモ
			String memo = service.getTodaysMemo(user.getId());
			form.setMemo(memo);
			

			return "main/stopwatch";
		} else {
			return "redirect:/user/login";
		}

	}

	@PostMapping("stopwatch")
	public String postMain(StopwatchForm form, Model model) {
		
		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		
		if (user != null) {
			int id = user.getId();

			// DBに保存
			service.setTodaysData(id, Time.valueOf(form.getTime()), form.getMemo());

			return "main/stopwatch";
		} else {
			return "redirect:/user/login";
		}

	}
	
	@GetMapping("mypage")
	public String getMypage(RecordForm form){
		//ログイン時のsessionが生成されているか確認
		user = (User) session.getAttribute("user");
		
		if (user != null) {
			//学習記録の各種データを取得
			int id = user.getId();
			String name = user.getName();
			Timestamp created_at = user.getCreated_at();
			Time totalTime =  service.getTotalTime(id);
			 List<Daily_Learning_Time> dlts = service.getAllData(id);
			
			// formのフィールドにセット
			form.setName(name);
			form.setCreated_at(created_at);
			form.setTotalTime(totalTime);
			form.setDlts(dlts);

			return "main/mypage";
		} else {
			return "redirect:/user/login";
		}
	}

	
}
