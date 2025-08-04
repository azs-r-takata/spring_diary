package com.example.diary.form;

import java.sql.Date;

import lombok.Data;

@Data
public class DiaryForm {
	private int diaryIdForm;
	private String diaryTitleForm;
	private String diaryContentForm;
	private Date diaryDateForm;
}