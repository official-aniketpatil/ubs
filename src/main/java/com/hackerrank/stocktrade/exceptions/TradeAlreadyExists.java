package com.hackerrank.stocktrade.exceptions;

public class TradeAlreadyExists extends RuntimeException {
	public TradeAlreadyExists(String message) {
		super(message);
	}
}
