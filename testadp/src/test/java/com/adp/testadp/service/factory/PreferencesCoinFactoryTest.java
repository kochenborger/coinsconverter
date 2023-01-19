package com.adp.testadp.service.factory;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.adp.testadp.coins.Vault;
import com.adp.testadp.enums.CoinsPreference;

@ExtendWith(MockitoExtension.class)
public class PreferencesCoinFactoryTest {

	PreferencesCoinFactory preferencesCoinFactory;
	
	@Mock
	Vault vault;
	
	
	@BeforeEach
	void setUp() {
		preferencesCoinFactory = new PreferencesCoinFactory(vault);
	}
	
	@Test
	void sortCoinsByPreferenceLessAmountCoinTest() {
		
		when(vault.getCoinsAllow()).thenReturn(List.of(new BigDecimal("0.01"), new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25")));
		
		List<BigDecimal> sortedList = preferencesCoinFactory.sortCoinsByPreference(CoinsPreference.LESS_AMOUNT_COIN);
		
		assertTrue(sortedList.get(0).equals(new BigDecimal("0.25")));
		
	}
	
	@Test
	void sortCoinsByPreferenceGreaterAmountCoinTest() {
		
		when(vault.getCoinsAllow()).thenReturn(List.of(new BigDecimal("0.01"), new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25")));
		
		List<BigDecimal> sortedList = preferencesCoinFactory.sortCoinsByPreference(CoinsPreference.GREATER_AMOUT_COIN);
		
		assertTrue(sortedList.get(0).equals(new BigDecimal("0.01")));
		
	}
}
