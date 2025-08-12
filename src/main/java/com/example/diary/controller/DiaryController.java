package com.example.diary.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.core.Conventions;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.entity.Diary;
import com.example.diary.form.DiaryForm;
import com.example.diary.security.UserDetailsImpl;
import com.example.diary.service.DiaryService;

@Controller
public class DiaryController {
	private final DiaryService diaryService;
	public DiaryController(DiaryService diaryService) {
		this.diaryService = diaryService;
	}
	
	//ホーム画面
	@GetMapping("/diary")
	public String home(Model model) {
		List<Diary> diaries = diaryService.getAllDiary();
		Collections.reverse(diaries);
		model.addAttribute("diaries", diaries);
		
		return "diaryView";
	} //End ホーム画面
	
	//詳細画面
	@GetMapping("/diary/{id}")
	public String detail(@PathVariable int id, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {
		model.addAttribute("diary", diaryService.getDiaryById(id).orElse(null));
		model.addAttribute("user", user);
		return "diaryDetailsView";
	} //End 詳細画面
	
	//新規投稿画面
	@GetMapping("/diary/new")
	public String Input(Model model) {
		if(!model.containsAttribute("diaryForm")) {
			model.addAttribute("diaryForm", new DiaryForm());
		}
		return "diaryInputView";
	} //End 新規投稿画面
	
	//編集画面
	@GetMapping("/diary/edit/{id}")
	public String editer(@PathVariable int id, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {
		if(id != user.getId()) {
			return "redirect:/diary/error403";
		}
		
		model.addAttribute("diary", diaryService.getDiaryById(id).orElse(null));
		
		if(!model.containsAttribute("diaryForm")) {
			model.addAttribute("diaryForm", diaryService.getEditDiary(id));
		}
		
		return "diaryUpdateView";
	} //End 編集画面
	
	//投稿保存処理
	@PostMapping("/diary/save")
	public String saveDiary(RedirectAttributes redirectAttributes,
			@Validated DiaryForm form, BindingResult result) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("diaryForm", form);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(form), result);
			
			return "redirect:/diary/new";
		}
		
		try {
			diaryService.inputDiary(form.getDiaryTitleForm(), form.getDiaryContentForm());
			redirectAttributes.addFlashAttribute("successMessage", "日記の登録完了");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}
		
		return "redirect:/diary";
	} //End 投稿保存処理
	
	//更新保存処理
	@PostMapping("/diary/update")
	public String updateDiary(RedirectAttributes redirectAttributes,
			@RequestParam("diaryId") int diaryId,
			@RequestParam("diaryUserId") int diaryUserId,
			@Validated DiaryForm form, BindingResult result) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("diaryForm", form);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(form), result);
			
			return "redirect:/diary/edit/" + diaryId;
		}
		
		try {
			diaryService.updateDiary(diaryId, form.getDiaryTitleForm(), form.getDiaryContentForm(), diaryUserId);
			redirectAttributes.addFlashAttribute("successMessage", "日記の更新完了");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}
		
		return "redirect:/diary";
	} //End 更新保存処理
	
	//削除処理
	@PostMapping("diary/delete/{id}")
	public String deleteDiary(@PathVariable int id, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal UserDetailsImpl user) {
		if(id != user.getId()) {
			return "redirect:/diary/error403";
		}
		
		try {
			diaryService.deleteDiary(id);
			redirectAttributes.addFlashAttribute("successMessage", "日記の削除完了");
		}catch(EmptyResultDataAccessException e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}
		
		return "redirect:/diary";
	} //End 削除処理
	
	@GetMapping("/diary/error403")
	public String error403() {
		return "error403";
	}
}
