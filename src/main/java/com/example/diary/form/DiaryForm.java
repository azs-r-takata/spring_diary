package com.example.diary.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DiaryForm {
	private int diaryId;
	
	@NotBlank(message = "タイトルを入力してください。")
	@Size(max = 10, message = "タイトルは10文字以内に収めてください。")
	private String diaryTitleForm;
	
	@NotBlank(message = "本文を入力してください。")
	private String diaryContentForm;
}