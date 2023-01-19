package com.adp.testadp.exception;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CoinNotFound extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoinNotFound(BigDecimal coin) {
		super("Coin " + coin + " not found in vault.");
	}
	
}
