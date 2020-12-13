package com.hackerrank.stocktrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.stocktrade.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long>{

	public List<Trade> findByUserId(Long id);
	public List<Trade> findAllBySymbol(String symbol);
	public Boolean existsBySymbol(String symbol);
	public List<Trade> findByUserIdAndSymbol(Long userId, String symbol);
}
