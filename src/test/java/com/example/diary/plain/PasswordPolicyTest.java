package com.example.diary.plain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PasswordPolicyTest {
	
    @Test
	void testPasswordPolicyNull() {
		assertThrows(NullPointerException.class, () -> { PasswordPolicy.validate(null); });
	}
    
	@Test
	void testPasswordPolicySuccess() {
		List<String> result = PasswordPolicy.validate("Abcdef1!");
		assertTrue(result.isEmpty());
	}
	
	@Test
	void testPasswordPolicyLength() {
		List<String> result = PasswordPolicy.validate("Abcde1!");
		assertTrue(result.contains("LENGTH"));
	}
	
	@Test
	void testPasswordPolicyUpper() {
		List<String> result = PasswordPolicy.validate("abcdef1!");
		assertTrue(result.contains("UPPER"));
	}
	
	@Test
	void testPasswordPolicyLower() {
		List<String> result = PasswordPolicy.validate("ABCDEF1!");
		assertTrue(result.contains("LOWER"));
	}
	
	@Test
	void testPasswordPolicyDigit() {
		List<String> result = PasswordPolicy.validate("Abcdefg!");
		assertTrue(result.contains("DIGIT"));
	}
	
	@Test
	void testPasswordPolicySymbol() {
		List<String> result = PasswordPolicy.validate("Abcdefg1");
		assertTrue(result.contains("SYMBOL"));
	}
	
	@Test
	void testPasswordPolicySpace() {
		List<String> result = PasswordPolicy.validate("Abcde 1!");
		assertTrue(result.contains("SPACE"));
	}
	
	@Test
	void testPasswordPolicyMix() {
		List<String> result = PasswordPolicy.validate("mix");
		assertTrue(result.contains("LENGTH"));
		assertTrue(result.contains("UPPER"));
		assertTrue(result.contains("DIGIT"));
		assertTrue(result.contains("SYMBOL"));
	}
}
