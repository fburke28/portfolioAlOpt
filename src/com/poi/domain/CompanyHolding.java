/* File: CompanyHolding.java
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
import java.util.List;

/**
 * Domain class used to represent Company holding data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Apr 03, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class CompanyHolding {

	private String symbol;
	private String companyDesc;
	private String sector;
	private String industry;
	private String marketCap;
	private BigDecimal price;
	private BigDecimal collabRating;
	private String classification;
	private String held;
	private Long quantity;
	private BigDecimal liquidity;
	private BigDecimal tradePrice;
	private BigDecimal userRating;
	private String ratingDesc;
	private List<IndividualHolding> individualHoldings;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCollabRating() {
		return collabRating;
	}

	public void setCollabRating(BigDecimal collabRating) {
		this.collabRating = collabRating;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getHeld() {
		return held;
	}

	public void setHeld(String held) {
		this.held = held;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getLiquidity() {
		return liquidity;
	}

	public void setLiquidity(BigDecimal liquidity) {
		this.liquidity = liquidity;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public BigDecimal getUserRating() {
		return userRating;
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

	public List<IndividualHolding> getIndividualHoldings() {
		return individualHoldings;
	}

	public void setIndividualHoldings(List<IndividualHolding> individualHoldings) {
		this.individualHoldings = individualHoldings;
	}

}