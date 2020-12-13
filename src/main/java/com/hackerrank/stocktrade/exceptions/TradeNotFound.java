package com.hackerrank.stocktrade.exceptions;

public class TradeNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8606252990132313345L;

	public TradeNotFound(String message) {
		super(message);
	}
}