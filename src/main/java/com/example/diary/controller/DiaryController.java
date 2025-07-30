package com.example.diary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {
	@GetMapping("/diary")
	public String home() {
		return "diaryView";
	}
	
	@GetMapping("/diary/new")
	public String Input() {
		return "diaryView";
	}
}
