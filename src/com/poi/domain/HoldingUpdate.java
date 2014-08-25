/* File: HoldingUpdate.java
* 
* Copyright 2010, Finbarr Burke
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

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to represent a user supplied holding.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Apr 07, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class HoldingUpdate implements Serializable {

	@Id private Long holdingKey;
	private String symbol;
	private String accountNumber;
	private String userName;
	@Transient private BigDecimal userRating;
	private String ratingDesc;
	private Long existingQuantity;
	private Long updatedQuantity;
	@Transient private BigDecimal buyAmount;
	@Transient private BigDecimal tradePrice;

	// Needed for objectify
	private String userRatingStr;
	private String buyAmountStr;
	private String tradePriceStr;

	public Long getHoldingKey() {
		return holdingKey;
	}

	public void setHoldingKey(Long holdingKey) {
		this.holdingKey = holdingKey;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getUserRating() {
		return userRating;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserRating(BigDecimal userRating) {
		this.userRating = userRating;
	}

	public String getRatingDesc() {
		return ratingDesc;
	}

	public void setRatingDesc(String ratingDesc) {
		this.ratingDesc = ratingDesc;
	}

	public Long getExistingQuantity() {
		return existingQuantity;
	}

	public void setExistingQuantity(Long existingQuantity) {
		this.existingQuantity = existingQuantity;
	}

	public Long getUpdatedQuantity() {
		return updatedQuantity;
	}

	public void setUpdatedQuantity(Long updatedQuantity) {
		this.updatedQuantity = updatedQuantity;
	}

	public BigDecimal getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(BigDecimal buyAmount) {
		this.buyAmount = buyAmount;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	// Objectify specific
	public void setObjectifyValues() {
		this.userRatingStr = userRating == null ? "" : userRating.toPlainString();
		this.buyAmountStr = buyAmount == null ? "" : buyAmount.toPlainString();
		this.tradePriceStr = tradePrice == null ? "" : tradePrice.toPlainString();
	}

	public String getUserRatingStr() {
		return userRatingStr;
	}

	public void setUserRatingStr(String userRatingStr) {
		this.userRatingStr = userRatingStr;
	}

	public String getBuyAmountStr() {
		return buyAmountStr;
	}

	public void setBuyAmountStr(String buyAmountStr) {
		this.buyAmountStr = buyAmountStr;
	}

	public String getTradePriceStr() {
		return tradePriceStr;
	}

	public void setTradePriceStr(String tradePriceStr) {
		this.tradePriceStr = tradePriceStr;
	}

}