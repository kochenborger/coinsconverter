package com.adp.testadp.controller.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adp.testadp.controller.IVaultController;
import com.adp.testadp.service.IVaultService;

@RestController
@RequestMapping(value = "/vault")
public class VaultController implements IVaultController {

	@Autowired
	private IVaultService vaultService;
	
	
	@GetMapping(path = "/coin", produces = "application/json")
	public HashMap<BigDecimal, Long> getVaultCoins() {
		
		return vaultService.getVaultCoins();
	}

	
	@PutMapping(path = "/coin/{coin}/quantity/{qtd}", produces = "application/json")
	public HashMap<BigDecimal, Long> addVaultCoins(@PathVariable BigDecimal coin, @PathVariable Long qtd) {
		
		return vaultService.addVaultCoins(coin, qtd);
	}
}
