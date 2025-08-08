package com.example.diary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diary.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByUserName(String userName);
}
