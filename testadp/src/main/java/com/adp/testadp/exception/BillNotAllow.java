package com.adp.testadp.exception;

public class BillNotAllow extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BillNotAllow(Long bill) {
		super("Bill " + bill + " not allow. Please choose a valid bill.");
	}
}
