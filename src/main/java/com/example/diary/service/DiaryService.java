package com.example.diary.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.diary.entity.Diary;
import com.example.diary.form.DiaryForm;
import com.example.diary.repository.DiaryRepository;

@Service
public class DiaryService {
	private final DiaryRepository diaryRepository;
	public DiaryService(DiaryRepository diaryRepository) {
		this.diaryRepository = diaryRepository;
	}
	
	public void inputDiary(String diaryTitle, String diaryContent) {
		if (diaryTitle == null || diaryTitle.isEmpty()) {
			throw new IllegalArgumentException("タイトルを入力してください。");
		}
		
		if (!diaryRepository.findByDiaryTitle(diaryTitle).isEmpty()) {
			throw new IllegalArgumentException("そのタイトルは既に使用されています。");
		}
		
		Date now = new Date();
		Diary diary = new Diary();
		diary.setDiaryTitle(diaryTitle);
		diary.setDiaryContent(diaryContent);
		diary.setDiaryDate(now);
		
		diaryRepository.save(diary);
	}
	
	public DiaryForm getEditDiary(int diaryId) {
		Optional<Diary> diaryOpt = diaryRepository.findById(diaryId);
		Diary entity = diaryOpt.get();
		
		DiaryForm form = new DiaryForm();
		form.setDiaryId(diaryId);
		form.setDiaryTitleForm(entity.getDiaryTitle());
		form.setDiaryContentForm(entity.getDiaryContent());
		return form;
	}
	
	public void updateDiary(int diaryId, String diaryTitle, String diaryContent) {
		if (diaryTitle == null || diaryTitle.isEmpty()) {
			throw new IllegalArgumentException("タイトルを入力してください。");
		}
		
		if (!diaryRepository.findByDiaryTitle(diaryTitle).isEmpty()) {
			throw new IllegalArgumentException("そのタイトルは既に使用されています。");
		}
		
		Date now = new Date();
		Diary diary = new Diary();
		diary.setDiaryId(diaryId);
		diary.setDiaryTitle(diaryTitle);
		diary.setDiaryContent(diaryContent);
		diary.setDiaryDate(now);
		
		diaryRepository.save(diary);
	}
	
	public List<Diary> getAllDiary(){
		return diaryRepository.findAll();
	}

	public Optional<Diary> getDiaryById(int diaryId) {
		return diaryRepository.findById(diaryId);
	} 
	
	public void deleteDiary(int diaryId) {
		diaryRepository.deleteById(diaryId);
	}
}
