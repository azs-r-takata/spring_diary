package com.example.diary.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.diary.entity.Diary;
import com.example.diary.repository.DiaryRepository;

@Service
public class DiaryService {
	private final DiaryRepository diaryRepository;
	public DiaryService(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}
	
	public void inputDiary(String diaryTitle, Timestamp diaryDate, String diaryContent) {
		if (diaryTitle == null || diaryTitle.isEmpty()) {
			throw new IllegalArgumentException("タイトルを入力してください。");
		}
		
		if (!diaryRepository.findByDiaryTitle(diaryTitle).isEmpty()) {
			throw new IllegalArgumentException("そのタイトルは既に使用されています。");
		}
		
		Diary diary = new Diary();
		diary.setDiaryTitle(diaryTitle);
		diary.setDiaryDate(diaryDate);
		diary.setDiaryContent(diaryContent);
		
		diaryRepository.save(diary);
	}
	
	public List<Diary> getAllDiary(){
		return diaryRepository.findAll();
	}

	public Optional<Diary> getDiaryById(int Id) {
		return diaryRepository.findById(Id);
	} 
}
