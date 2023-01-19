package com.adp.testadp.service.factory;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.adp.testadp.coins.Vault;
import com.adp.testadp.enums.CoinsPreference;

@Component
public class PreferencesCoinFactory {

	private Vault vault;
	
	public PreferencesCoinFactory(Vault vault) {
		this.vault = vault;
	}
	
	public List<BigDecimal> sortCoinsByPreference(CoinsPreference coinsPreference) {
		
		List<BigDecimal> sortedCoins = null;
		
		if (CoinsPreference.LESS_AMOUNT_COIN.equals(coinsPreference)) {
			//Sort from bigger to smaller coins to use less coins as possible
			sortedCoins = vault.getCoinsAllow().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		} else {
			//Sort from smaller to bigger coins to use more coins as possible
			sortedCoins = vault.getCoinsAllow().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
		}
		
		return sortedCoins;
	}
	
}
