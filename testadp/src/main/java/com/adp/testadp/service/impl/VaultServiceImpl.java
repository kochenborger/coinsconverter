package com.adp.testadp.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adp.testadp.coins.Vault;
import com.adp.testadp.service.IVaultService;
import lombok.Data;

@Service
@Data
public class VaultServiceImpl implements IVaultService {

	@Autowired
	private Vault vault;
	
	@Override
	public HashMap<BigDecimal, Long> getVaultCoins() {
		return vault.getCoins();
	}

	@Override
	public HashMap<BigDecimal, Long> addVaultCoins(BigDecimal coin, Long qtd) {
		return vault.addCoins(coin, qtd);
	}


	
}
