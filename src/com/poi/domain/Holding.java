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

package com.poi.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Domain class used to represent Holding data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Feb 16, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class Holding {

	private Long holdingKey;
	private String accountNumber;
	private String symbol;
	private Long quantity;
	private BigDecimal tradePrice;
	private BigDecimal price;
	private BigDecimal priceChange;
	private BigDecimal share;
	private Date updateTmstp;

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
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange;
	}

	public BigDecimal getShare() {
		return share;
	}

	public void setShare(BigDecimal share) {
		this.share = share;
	}

	public Date getUpdateTmstp() {
		return updateTmstp;
	}

	public void setUpdateTmstp(Date updateTmstp) {
		this.updateTmstp = updateTmstp;
	}

}