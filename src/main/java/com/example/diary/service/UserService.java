package com.example.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.diary.entity.User;
import com.example.diary.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void inputUser(String userName, String password) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPassword(password);
		user.setUserRole("ROLE_GENERAL");
		
		userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		return userRepository.findAll();
	}

}
