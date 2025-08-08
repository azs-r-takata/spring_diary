package com.example.diary.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
	@NotBlank(message = "ユーザー名を入力してください。")
	private String userName;
	
	@NotBlank(message = "パスワードを入力してください。")
	@Size(min = 8, message = "パスワードは8文字以上で入力してください。")
	private String password;
}
