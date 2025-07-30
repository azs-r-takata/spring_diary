package com.example.diary.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "diaries")
@Data
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Integer diaryId;
	
	@Column(name = "diary_title")
	private String diaryTitle;
	
	@Column(name = "diary_date")
	private Timestamp diaryDate;
	
	@Column(name = "diary_content")
	private String diaryContent;
}
