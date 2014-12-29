package com.po.domain;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Data for user stock ratings.
 * 
 * @author fburke
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class StockRating implements Serializable {

	@Id private Long key;
	private String userName;
	private String symbol;
	private Double stockRating;
	private String comment;

	public StockRating() {
		super();
	}

	public StockRating(Long key, String userName, String symbol,
			Double stockRating, String comment) {
		super();
		this.key = key;
		this.userName = userName;
		this.symbol = symbol;
		this.stockRating = stockRating;
		this.comment = comment;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getStockRating() {
		return stockRating;
	}

	public void setStockRating(Double stockRating) {
		this.stockRating = stockRating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockRating [key=");
		builder.append(key);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", symbol=");
		builder.append(symbol);
		builder.append(", stockRating=");
		builder.append(stockRating);
		builder.append(", comment=");
		builder.append(comment);
		builder.append("]");
		return builder.toString();
	}

}