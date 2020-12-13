package com.hackerrank.stocktrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.exceptions.TradeAlreadyExists;
import com.hackerrank.stocktrade.exceptions.TradeNotFound;
import com.hackerrank.stocktrade.exceptions.UserNotFoundException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

	@Autowired
	private TradeService tradeService;

	@GetMapping
	public ResponseEntity<List<Trade>> getAll() {
		List<Trade> trades = tradeService.getAll();
		return ResponseEntity.ok(trades);
	}

	@PostMapping
	public ResponseEntity<Trade> add(@RequestBody Trade trade) {
		try {
			tradeService.add(trade);
			return ResponseEntity.created(null).build();
		} catch (TradeAlreadyExists exception) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Trade> get(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(tradeService.getById(id));
		} catch (TradeNotFound ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Trade>> getByUserId(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(tradeService.getByUserId(userId));
		} catch (UserNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/stocks/{stockSymbol}")
	public ResponseEntity<List<Trade>> getAllByStockSymbol(@PathVariable String stockSymbol) {
		try {
			return ResponseEntity.ok(tradeService.getByStockSymbol(stockSymbol));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/users/{userId}/stocks/{stockSymbol}")
	public ResponseEntity<List<Trade>> getAllByUserIdAndStockSymbol(@PathVariable Long userId,
			@PathVariable String stockSymbol) {
		try {
			return ResponseEntity.ok(tradeService.getByUserIdAndStockSymbol(userId, stockSymbol));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
