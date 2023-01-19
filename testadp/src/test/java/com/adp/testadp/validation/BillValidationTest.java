package com.adp.testadp.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.adp.testadp.coins.Vault;
import com.adp.testadp.exception.BillNotAllow;
import com.adp.testadp.exception.CoinsNotEnougth;

@ExtendWith(MockitoExtension.class)
public class BillValidationTest {

	BillValidation validation;
	
	@Mock
	Vault vault;
	
	@BeforeEach
	void setUp() {
		validation = new BillValidation(vault);
	}
	
	/**
	 * Validate if the bill is a valid value
	 */
	@Test
	void validateBillAllowTest() {
		
		when(vault.getBillsAllow()).thenReturn(List.of(1L, 2L, 5L, 10L, 20L, 50L, 100L));
		
		assertTrue(validation.validateBillAllow(1L));
		assertTrue(validation.validateBillAllow(2L));
		assertTrue(validation.validateBillAllow(5L));
		assertTrue(validation.validateBillAllow(10L));
		assertTrue(validation.validateBillAllow(20L));
		assertTrue(validation.validateBillAllow(50L));
		assertTrue(validation.validateBillAllow(100L));
		
		try {
			assertTrue(validation.validateBillAllow(3L));	
		} catch (Exception e) {
			assertTrue(e instanceof BillNotAllow);
		}
	}
	
	/*
	 * Validate if there are an enugth quantity of coins for the bill parameter
	 */
	@Test
	void validateCoinsQuantityTest() {
		
		when(vault.sumCoinsAvailable()).thenReturn(new BigDecimal("50.0"));
		
		assertTrue(validation.validateCoinsQuantity(10L));
		
		try {
			validation.validateCoinsQuantity(51L);
		} catch (Exception e) {
			assertTrue(e instanceof CoinsNotEnougth);
		}
	}
}
