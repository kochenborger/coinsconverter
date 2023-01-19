package com.adp.testadp.controller;

import java.math.BigDecimal;
import java.util.HashMap;

public interface IVaultController {

	public HashMap<BigDecimal, Long> getVaultCoins();
	public HashMap<BigDecimal, Long> addVaultCoins(BigDecimal coin, Long qtd);
	
}
