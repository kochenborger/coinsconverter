package com.adp.testadp.service.imple;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
import com.adp.testadp.service.factory.PreferencesCoinFactory;
import com.adp.testadp.service.impl.SortAmountCoinsChangerServiceImpl;
import com.adp.testadp.validation.BillValidation;


@ExtendWith(MockitoExtension.class)
public class SortAmountCoinsChangerServiceImplTest {

	SortAmountCoinsChangerServiceImpl sortAmountCoinsChangerServiceImpl;
	
	private Vault vault;

	@Mock
	private BillValidation coinValidation;
	
	@Mock
	private PreferencesCoinFactory preferencesCoinFactory;
	
	@BeforeEach
	void setUp() {
		this.vault = new Vault();
		this.vault.reinitializeQtdCoins();
		sortAmountCoinsChangerServiceImpl = new SortAmountCoinsChangerServiceImpl(vault, coinValidation, preferencesCoinFactory);
	}
	
	@Test
	void changeBillsTest() {
		
		when(coinValidation.validateBillAllow(anyLong())).thenReturn(Boolean.TRUE);
		when(coinValidation.validateCoinsQuantity(anyLong())).thenReturn(Boolean.TRUE);
		when(preferencesCoinFactory.sortCoinsByPreference(any())).thenReturn(List.of(new BigDecimal("0.01"), new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25")));
		
		sortAmountCoinsChangerServiceImpl.changeBills(20L, CoinsPreference.GREATER_AMOUT_COIN);
		assertTrue(vault.getCoins().get(new BigDecimal("0.25")).equals(84L));
		assertTrue(vault.getCoins().get(new BigDecimal("0.10")).equals(0L));
		assertTrue(vault.getCoins().get(new BigDecimal("0.05")).equals(0L));
		assertTrue(vault.getCoins().get(new BigDecimal("0.01")).equals(0L));
		
		sortAmountCoinsChangerServiceImpl.changeBills(20L, CoinsPreference.GREATER_AMOUT_COIN);
		assertTrue(vault.getCoins().get(new BigDecimal("0.25")).equals(4L));
		assertTrue(vault.getCoins().get(new BigDecimal("0.10")).equals(0L));
		assertTrue(vault.getCoins().get(new BigDecimal("0.05")).equals(0L));
		assertTrue(vault.getCoins().get(new BigDecimal("0.01")).equals(0L));
	}
}
