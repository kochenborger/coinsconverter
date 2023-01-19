package com.adp.testadp.service;

import java.math.BigDecimal;
import java.util.HashMap;

public interface IVaultService {

	public HashMap<BigDecimal, Long> getVaultCoins();
	public HashMap<BigDecimal, Long> addVaultCoins(BigDecimal coin, Long qtd);
	
}
