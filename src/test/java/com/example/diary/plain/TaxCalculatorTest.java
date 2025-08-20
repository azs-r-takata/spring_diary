package com.example.diary.plain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;

public class TaxCalculatorTest {
	@Test
	void testTaxCalculatorNetNull() {
		assertThrows(NullPointerException.class, () -> { TaxCalculator.addTax(null, 50, RoundingMode.CEILING); });
	}
	
	@Test
	void testTaxCalculatorModeNull() {
		assertThrows(NullPointerException.class, () -> { TaxCalculator.addTax(new BigDecimal("100"), 50, null); });
	}
	
	@Test
	void testTaxCalculatorPersent() {
		assertThrows(IllegalArgumentException.class, () -> { TaxCalculator.addTax(new BigDecimal("100"), 101, RoundingMode.CEILING); });
	}
	
	@Test
	void testTaxCalculatorTax() {
		System.out.println(TaxCalculator.addTax(new BigDecimal("100"), 10, RoundingMode.CEILING));
		assertEquals(new BigDecimal("110"), TaxCalculator.addTax(new BigDecimal("100"), 10, RoundingMode.CEILING));
	}
}
