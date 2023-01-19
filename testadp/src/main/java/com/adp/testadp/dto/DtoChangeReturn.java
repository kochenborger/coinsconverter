package com.adp.testadp.dto;

import java.math.BigDecimal;
import java.util.HashMap;

public class DtoChangeReturn {
	
	private HashMap<BigDecimal, Integer> coinsUsed = new HashMap<>();
	private HashMap<BigDecimal, Long> coinsAvailable = new HashMap<>();
	
	public HashMap<BigDecimal, Integer> getCoinsUsed() {
		return coinsUsed;
	}
	public void setCoinsUsed(HashMap<BigDecimal, Integer> coinsUsed) {
		this.coinsUsed = coinsUsed;
	}
	public HashMap<BigDecimal, Long> getCoinsAvailable() {
		return coinsAvailable;
	}
	public void setCoinsAvailable(HashMap<BigDecimal, Long> coinsAvailable) {
		this.coinsAvailable = coinsAvailable;
	}

}
