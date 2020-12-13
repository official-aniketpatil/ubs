package com.hackerrank.stocktrade.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.hackerrank.stocktrade.exceptions.TradeAlreadyExists;
import com.hackerrank.stocktrade.exceptions.TradeNotFound;
import com.hackerrank.stocktrade.exceptions.UserNotFoundException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;

@Component
public class TradeService {

	@Autowired
	private TradeRepository repository;
	
	@Autowired
	private UserRepository userRepository;

	public List<Trade> getAll() {
//		sort by id
		List<Trade> trades = repository.findAll();
		return trades;
	}

	public Trade add(Trade trade) {
		if(repository.existsById(trade.getId())) {
			throw new TradeAlreadyExists("trade exists");
		}
		return repository.save(trade);
	}
	
	public Trade getById(Long id) {
		if(!repository.existsById(id)) {
			throw new TradeNotFound("trade dosen't exists");
		}
		return repository.getOne(id);
	}
	
	public List<Trade> getByUserId(Long id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException("user not found");
		}
		return repository.findByUserId(id);
	}
	
	public List<Trade>getByStockSymbol(String symbol) {
//	sort by id
		if(!repository.existsBySymbol(symbol)){
			throw new RuntimeException("not exist by symbol");
		}
		return repository.findAllBySymbol(symbol);
	}
	
	public List<Trade> getByUserIdAndStockSymbol(Long userId, String symbol) {
		return repository.findByUserIdAndSymbol(userId, symbol);
	}

	public ResponseEntity<String> erase() {
		repository.deleteAll();
		return ResponseEntity.ok().build();
	}
}
