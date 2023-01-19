package com.adp.testadp.exception;

import java.math.BigDecimal;

public class CoinsNotEnougth extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoinsNotEnougth(BigDecimal maxCoins, Long bill) {
		super("Coins not enougth for this operation. The maximun value of coins is: " + maxCoins);
	}
	
}
