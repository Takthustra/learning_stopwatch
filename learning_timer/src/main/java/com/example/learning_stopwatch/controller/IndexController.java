package com.example.learning_stopwatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {

	@GetMapping()
	public String getIndex(Model model) {
		model.addAttribute("catchphrase1","君の学びの軌跡を刻む。");
		model.addAttribute("catchphrase2","さぁ、今から始めよう。");
		
		return "index/index";

	}

}
