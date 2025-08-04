package com.example.diary.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.entity.Diary;
import com.example.diary.form.DiaryForm;
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
		
		if(!model.containsAttribute("diaryForm")) {
			model.addAttribute("diaryForm", new DiaryForm());
		}
		
		return "diaryView";
	}
	
	@GetMapping("/diary/{id}")
	public String detail(@PathVariable int id, Model model) {
		model.addAttribute("diary", diaryService.getDiaryById(id).orElse(null));
		return "diaryDetailsView";
	}
	
	@GetMapping("/diary/new")
	public String Input() {
		return "diaryInputView";
	}
	
	@GetMapping("/diary/edit/{id}")
	public String editer(@PathVariable int id, Model model) {
		model.addAttribute("diary", diaryService.getDiaryById(id).orElse(null));
		return "diaryUpdateView";
	}
	
	@PostMapping("/diary/save")
	public String saveDiary(RedirectAttributes redirectAttributes,
			DiaryForm form) {
			//@RequestParam("diaryTitle") String diaryTitle,
			//@RequestParam("diaryContent") String diaryContent) {
		try {
			diaryService.inputDiary(form.getDiaryTitleForm(), form.getDiaryContentForm());
			//diaryService.inputDiary(diaryTitle, diaryContent);
			redirectAttributes.addFlashAttribute("successMessage", "日記の登録完了");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}
		
		return "redirect:/diary";
	}
	
	@PostMapping("/diary/update")
	public String updateDiary(RedirectAttributes redirectAttributes,
			@RequestParam("diaryId") int diaryId,
			@RequestParam("diaryTitle") String diaryTitle,
			@RequestParam("diaryContent") String diaryContent) {
		try {
			diaryService.updateDiary(diaryId, diaryTitle, diaryContent);
			redirectAttributes.addFlashAttribute("successMessage", "日記の更新完了");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}
		
		return "redirect:/diary";
	}
	
	@PostMapping("diary/delete/{id}")
	public String deleteDiary(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {
			diaryService.deleteDiary(id);
			redirectAttributes.addFlashAttribute("successMessage", "日記の削除完了");
		}catch(EmptyResultDataAccessException e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}
		
		return "redirect:/diary";
	}
}
