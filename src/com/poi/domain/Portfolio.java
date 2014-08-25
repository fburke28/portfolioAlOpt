/* File: Portfolio.java
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
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.annotation.Entity;

/**
 * Domain class used to represent Portfolio data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Feb 16, 2011   Finbarr Burke       Initial Version<br><PRE>
 * <PRE>Mar 27, 2011   Finbarr Burke       Added portfolio constraints<br><PRE>
 * 
 * @author A412060
 * 
 */
@SuppressWarnings("serial")
@Repository
@Entity
public class Portfolio implements Serializable {

	private String userName;
	@Id private String accountNumber;
	@Transient private BigDecimal investment;
	@Transient private BigDecimal cashTotal;
	@Transient private BigDecimal equityTotal;
	@Transient private BigDecimal totalValue;
	@Transient private BigDecimal dailyPercentChange;
	@Transient private BigDecimal percentChanged;
	@Transient private BigDecimal maxPercentSingleStock;
	@Transient private BigDecimal maxPercentSector;
	@Transient private BigDecimal dailyVolatility;
	private String preferredSector;
	private Date updateTmstp;

	// Needed for objectify
	private String investmentStr;
	private String cashTotalStr;
	private String equityTotalStr;
	private String totalValueStr;
	private String dailyPercentChangeStr;
	private String percentChangedStr;
	private String maxPercentSingleStockStr;
	private String maxPercentSectorStr;
	private String dailyVolatilityStr;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getInvestment() {
		return investment;
	}

	public void setInvestment(BigDecimal investment) {
		this.investment = investment;
	}

	public BigDecimal getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(BigDecimal cashTotal) {
		this.cashTotal = cashTotal;
	}

	public BigDecimal getEquityTotal() {
		return equityTotal;
	}

	public void setEquityTotal(BigDecimal equityTotal) {
		this.equityTotal = equityTotal;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public BigDecimal getDailyPercentChange() {
		return dailyPercentChange;
	}

	public void setDailyPercentChange(BigDecimal dailyPercentChange) {
		this.dailyPercentChange = dailyPercentChange;
	}

	public BigDecimal getPercentChanged() {
		return percentChanged;
	}

	public void setPercentChanged(BigDecimal percentChanged) {
		this.percentChanged = percentChanged;
	}

	public BigDecimal getMaxPercentSingleStock() {
		return maxPercentSingleStock;
	}

	public void setMaxPercentSingleStock(BigDecimal maxPercentSingleStock) {
		this.maxPercentSingleStock = maxPercentSingleStock;
	}

	public BigDecimal getMaxPercentSector() {
		return maxPercentSector;
	}

	public void setMaxPercentSector(BigDecimal maxPercentSector) {
		this.maxPercentSector = maxPercentSector;
	}

	public BigDecimal getDailyVolatility() {
		return dailyVolatility;
	}

	public void setDailyVolatility(BigDecimal dailyVolatility) {
		this.dailyVolatility = dailyVolatility;
	}

	public String getPreferredSector() {
		return preferredSector;
	}

	public void setPreferredSector(String preferredSector) {
		this.preferredSector = preferredSector;
	}

	public Date getUpdateTmstp() {
		return updateTmstp;
	}

	public void setUpdateTmstp(Date updateTmstp) {
		this.updateTmstp = updateTmstp;
	}

	// Objectify specific
	public void setObjectifyValues() {
		this.investmentStr = investment == null ? "" : investment.toPlainString();
		this.cashTotalStr = cashTotal == null ? "" : cashTotal.toPlainString();
		this.equityTotalStr = equityTotal == null ? "" : equityTotal.toPlainString();
		this.totalValueStr = totalValue == null ? "" : totalValue.toPlainString();
		this.dailyPercentChangeStr = dailyPercentChange == null ? "" : dailyPercentChange.toPlainString();
		this.percentChangedStr = percentChanged == null ? "" : percentChanged.toPlainString();
		this.maxPercentSingleStockStr = maxPercentSingleStock == null ? "" : maxPercentSingleStock.toPlainString();
		this.maxPercentSectorStr = maxPercentSector == null ? "" : maxPercentSector.toPlainString();
		this.dailyVolatilityStr = dailyVolatility == null ? "" : dailyVolatility.toPlainString();
	}

	public String getInvestmentStr() {
		return investmentStr;
	}

	public void setInvestmentStr(String investmentStr) {
		this.investmentStr = investmentStr;
	}

	public String getCashTotalStr() {
		return cashTotalStr;
	}

	public void setCashTotalStr(String cashTotalStr) {
		this.cashTotalStr = cashTotalStr;
	}

	public String getEquityTotalStr() {
		return equityTotalStr;
	}

	public void setEquityTotalStr(String equityTotalStr) {
		this.equityTotalStr = equityTotalStr;
	}

	public String getTotalValueStr() {
		return totalValueStr;
	}

	public void setTotalValueStr(String totalValueStr) {
		this.totalValueStr = totalValueStr;
	}

	public String getDailyPercentChangeStr() {
		return dailyPercentChangeStr;
	}

	public void setDailyPercentChangeStr(String dailyPercentChangeStr) {
		this.dailyPercentChangeStr = dailyPercentChangeStr;
	}

	public String getPercentChangedStr() {
		return percentChangedStr;
	}

	public void setPercentChangedStr(String percentChangedStr) {
		this.percentChangedStr = percentChangedStr;
	}

	public String getMaxPercentSingleStockStr() {
		return maxPercentSingleStockStr;
	}

	public void setMaxPercentSingleStockStr(String maxPercentSingleStockStr) {
		this.maxPercentSingleStockStr = maxPercentSingleStockStr;
	}

	public String getMaxPercentSectorStr() {
		return maxPercentSectorStr;
	}

	public void setMaxPercentSectorStr(String maxPercentSectorStr) {
		this.maxPercentSectorStr = maxPercentSectorStr;
	}

	public String getDailyVolatilityStr() {
		return dailyVolatilityStr;
	}

	public void setDailyVolatilityStr(String dailyVolatilityStr) {
		this.dailyVolatilityStr = dailyVolatilityStr;
	}

}