package com.adp.testadp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adp.testadp.coins.Vault;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Data
@Component
public class VaultInitConfig {

	@Autowired
	private Vault vault;
	
	@PostConstruct
	private void PostConstruct() {
		vault.reinitializeQtdCoins();
	}
	
}
