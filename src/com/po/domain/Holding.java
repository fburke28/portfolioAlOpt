/* File: Holding.java
* 
* Copyright 2011, Finbarr Burke
* All Rights Reserved
*
* This software and all information contained herein is the property
* of Finbarr Burke.
*
*			  Restricted Rights Legend
*			  ------------------------
* Use, duplication, or disclosure by the Government is subject to
* restrictions as set forth in paragraph (b)(3)(B) of the Rights in
* Technical Data and Computer Software clause in DAR 7-104.9(a).
*/
package com.po.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to represent Holding data.
 * 
 * @author Finbarr Burke
 * 
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class Holding implements Serializable {

	@Id private Long holdingKey;
	private String accountNumber;
	private String symbol;
	private Long quantity;
	@Transient private BigDecimal tradePrice;
	@Transient private BigDecimal price;
	@Transient private BigDecimal priceChange;
	@Transient private BigDecimal share;
	private Date updateTmstp;

	// Needed for objectify which doesn't support BigDecimal type.
	private String tradePriceStr;
	private String priceStr;
	private String priceChangeStr;
	private String shareStr;

	public void setDbHoldingData(HoldingUpdate dbHolding) {
		this.accountNumber = dbHolding.getAccountNumber();
		this.symbol = dbHolding.getSymbol();
		this.quantity = dbHolding.getUpdatedQuantity();
		this.tradePrice = new BigDecimal(dbHolding.getTradePriceStr());
	}

	public Long getHoldingKey() {
		return holdingKey;
	}

	public void setHoldingKey(Long holdingKey) {
		this.holdingKey = holdingKey;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
		this.tradePriceStr = tradePrice == null ? "" : tradePrice.toPlainString();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
		this.priceStr = price == null ? "" : price.toPlainString();
	}

	public BigDecimal getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange;
		this.priceChangeStr = priceChange == null ? "" : priceChange.toPlainString();
	}

	public BigDecimal getShare() {
		return share;
	}

	public void setShare(BigDecimal share) {
		this.share = share;
		this.shareStr = share == null ? "" : share.toPlainString();
	}

	public Date getUpdateTmstp() {
		return updateTmstp;
	}

	public void setUpdateTmstp(Date updateTmstp) {
		this.updateTmstp = updateTmstp;
	}

	// Objectify specific

	public String getTradePriceStr() {
		return tradePriceStr;
	}

	public void setTradePriceStr(String tradePriceStr) {
		this.tradePriceStr = tradePriceStr;
		this.tradePrice = tradePriceStr == null ? null : new BigDecimal(tradePriceStr);
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
		this.price = priceStr == null ? null : new BigDecimal(priceStr);
	}

	public String getPriceChangeStr() {
		return priceChangeStr;
	}

	public void setPriceChangeStr(String priceChangeStr) {
		this.priceChangeStr = priceChangeStr;
		this.priceChange = priceChangeStr == null ? null : new BigDecimal(priceChangeStr);
	}

	public String getShareStr() {
		return shareStr;
	}

	public void setShareStr(String shareStr) {
		this.shareStr = shareStr;
		this.share = shareStr == null ? null : new BigDecimal(shareStr);
	}

}