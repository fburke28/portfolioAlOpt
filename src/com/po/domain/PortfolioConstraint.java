/* File: PortfolioConstraint.java
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

import java.math.BigDecimal;
import java.util.Date;

/**
 * Domain class used to represent portfolio constraint data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Oct 30, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class PortfolioConstraint {

	private String accountNumber;
	private BigDecimal maxPercentSingleStock;
	private BigDecimal maxPercentSector;
	private BigDecimal dailyVolatility;
	private String preferredSector;
	private Date updateTmstp;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

}