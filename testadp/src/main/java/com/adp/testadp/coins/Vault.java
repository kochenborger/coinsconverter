package com.adp.testadp.coins;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Component;
import com.adp.testadp.exception.CoinNotFound;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class Vault {

	private final List<BigDecimal> coinsAllow = List.of(new BigDecimal("0.01"), new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"));
	private List<Long> billsAllow = List.of(1L, 2L, 5L, 10L, 20L, 50L, 100L);
	
	private static HashMap<BigDecimal, Long> COINS;
	
	public Boolean reinitializeQtdCoins() {
		COINS = new HashMap<>();
		
		coinsAllow.stream().forEach(coinAllow -> {
			COINS.put(coinAllow, 100L);
		});
		
		return Boolean.TRUE;
	}
	
	public HashMap<BigDecimal, Long> getCoins() {
		return COINS;
	}

	public HashMap<BigDecimal, Long> addCoins(BigDecimal coin, Long qtd) {
		if (COINS.containsKey(coin)) {
			COINS.put(coin, COINS.get(coin) + qtd);
		} else {
			throw new CoinNotFound(coin);
		}
		return COINS;
	}
	
	public HashMap<BigDecimal, Long> subCoins(BigDecimal coin, Long qtd) {
		if (COINS.containsKey(coin)) {
			if ( (COINS.get(coin) - qtd) < 0 ) {
				COINS.put(coin, 0L);
			} else {
				COINS.put(coin, COINS.get(coin) - qtd);				
			}
				
		} else {
			throw new CoinNotFound(coin);
		}
		return COINS;
	}

	public List<Long> getBillsAllow() {
		return billsAllow;
	}

	public List<BigDecimal> getCoinsAllow() {
		return this.coinsAllow;
	}

	/**
	 * Sum all values calculation the result according the quantity of coins for each case
	 * 
	 * for example:
	 * 0,25 : 100 coins
	 * 0,10 : 100 coins
	 * 
	 * will return (0,25*100) + (0,10*100)
	 * 
	 * @return
	 */
	public BigDecimal sumCoinsAvailable() {

		BigDecimal sum = new BigDecimal("0.00");
		
		for (BigDecimal key : COINS.keySet()) {
			sum = sum.add(key.multiply(new BigDecimal(COINS.get(key))));
		}
		
		return sum;
	}

	
	
}
