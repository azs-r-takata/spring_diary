package com.example.diary.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.diary.entity.Diary;
import com.example.diary.service.DiaryService;

@Controller
public class DiaryController {
	private final DiaryService diaryService;
	public DiaryController(DiaryService diaryService) {
		this.diaryService = diaryService;
	}
	
	@GetMapping("/diary")
	public String home(Model model) {
		List<Diary> diaries = diaryService.getAllDiary();
		Collections.reverse(diaries);
		model.addAttribute("diaries", diaries);
		return "diaryView";
	}
	
	@GetMapping("/diary/{id}}")
	public String detail(@PathVariable int Id, Model model) {
		model.addAttribute("diaries", diaryService.getDiaryById(Id).orElse(null));
		return "diaryDetailsView";
	}
	
	@GetMapping("/diary/new")
	public String Input() {
		return "diaryInputView";
	}
}
