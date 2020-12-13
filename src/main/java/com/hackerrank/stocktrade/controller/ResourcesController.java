package com.hackerrank.stocktrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.service.TradeService;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    
	@Autowired
	private TradeService tradeService;
	
	@DeleteMapping
	public ResponseEntity<String> erase() {
    	tradeService.erase();
    	return ResponseEntity.ok().build();
    }
}
