package com.example.diary.plain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class PasswordPolicy {
	private PasswordPolicy() {}
	private static final String SYMBOLS = "!@#S%^&*";
	
	/**
	 * 条件を満たせば空リスト、
違反があれば "LENGTH","UPPER","LOWER","DIGIT","SYMBOL","SPACE"を返します。
     * nullは許容せず、NullPointerExceptionを投げます。
	 */
	public static List<String> validate(String raw){
		Objects.requireNonNull(raw, "raw must not be null");
		List<String> violations = new ArrayList<>();
		
		if (raw.length() < 8) violations.add("LENGTH");
		
		boolean hasUpper = false, hasLower = false, hasDigit = false, hasSymbol = false, hasSpace = false;
		
		for (int i = 0; i < raw.length(); i++) {
			char c = raw.charAt(i);
			if (Character.isUpperCase(c)) hasUpper = true;
			else if (Character.isLowerCase(c)) hasLower = true;
			else if (Character.isDigit(c)) hasDigit = true;
			
			if (Character.isWhitespace(c)) hasSpace = true;
			if (SYMBOLS.indexOf(c) >= 0) hasSymbol = true;
		}
		
		if (!hasUpper) violations.add("UPPER");
		if (!hasLower) violations.add("LOWER");
		if (!hasDigit) violations.add("DIGIT");
		if (!hasSymbol) violations.add("SYMBOL");
		if (hasSpace) violations.add("SPACE");
		
		return violations;
	}
}
