package com.example.diary.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.diary.form.UserForm;
import com.example.diary.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		if(!model.containsAttribute("userForm")) {
			model.addAttribute("userForm", new UserForm());
		}
		return "auth/register";
	}
	
	@PostMapping("/registing")
	public String registing(RedirectAttributes redirectAttributes,
			@Validated UserForm form, BindingResult result) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("userForm", form);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(form), result);
			
			return "redirect:/register";
		}
		
		try {
			userService.inputUser(form.getUserName(), form.getPassword());
			redirectAttributes.addFlashAttribute("successMessage", "日記の登録完了");
		} catch (IllegalArgumentException e) {
			redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
		}
		
		return "redirect:/login";
	}
}
