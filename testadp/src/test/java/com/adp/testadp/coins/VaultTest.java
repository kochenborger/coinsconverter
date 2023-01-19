package com.adp.testadp.coins;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adp.testadp.exception.CoinNotFound;

@ExtendWith(MockitoExtension.class)
public class VaultTest {

	Vault vault;
	
	@BeforeEach
	void setUp() {
		vault = new Vault();
		vault.reinitializeQtdCoins();
	}
	
	@Test
	void addCoinsTest() {
		
		vault.addCoins(new BigDecimal("0.25"), 100L);
		assertTrue(vault.getCoins().get(new BigDecimal("0.25")).equals(200L));
		
		try {
			vault.addCoins(new BigDecimal("0.30"), 100L);
		} catch (Exception e) {
			assertTrue(e instanceof CoinNotFound);
		}
	}
	
	
	@Test
	void subCoinsTest() {
		
		vault.subCoins(new BigDecimal("0.25"), 100L);
		assertTrue(vault.getCoins().get(new BigDecimal("0.25")).equals(0L));
		
		vault.subCoins(new BigDecimal("0.10"), 150L);
		assertTrue(vault.getCoins().get(new BigDecimal("0.10")).equals(0L));
		
		try {
			vault.addCoins(new BigDecimal("0.30"), 100L);
		} catch (Exception e) {
			assertTrue(e instanceof CoinNotFound);
		}
	}
	
	@Test
	void sumCoinsAvailableTest() {
		assertTrue(vault.sumCoinsAvailable().longValue() == 41l);
	}
}
