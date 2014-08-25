/* File: UserRating.java
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

/**
 * Domain class used to represent user rating data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Apr 23, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class UserRating {

	private Long rank;
	private String userName;
	private String accountNumber;
	private String firstName;
	private String lastName;
	private BigDecimal investment;
	private BigDecimal cashTotal;
	private BigDecimal equityTotal;
	private BigDecimal totalValue;
	private BigDecimal dailyPercentChanged;
	private BigDecimal percentChanged;
	private String highestRatedStock;
	private String preferredSector;
	private BigDecimal userRating;

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public BigDecimal getDailyPercentChanged() {
		return dailyPercentChanged;
	}

	public void setDailyPercentChanged(BigDecimal dailyPercentChanged) {
		this.dailyPercentChanged = dailyPercentChanged;
	}

	public BigDecimal getPercentChanged() {
		return percentChanged;
	}

	public void setPercentChanged(BigDecimal percentChanged) {
		this.percentChanged = percentChanged;
	}

	public String getHighestRatedStock() {
		return highestRatedStock;
	}

	public void setHighestRatedStock(String highestRatedStock) {
		this.highestRatedStock = highestRatedStock;
	}

	public String getPreferredSector() {
		return preferredSector;
	}

	public void setPreferredSector(String preferredSector) {
		this.preferredSector = preferredSector;
	}

	public BigDecimal getUserRating() {
		return userRating;
	}

	public void setUserRating(BigDecimal userRating) {
		this.userRating = userRating;
	}

}