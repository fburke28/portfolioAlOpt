/* File: SecurityRating.java
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

import java.math.BigDecimal;
import java.util.Date;

/**
 * Domain class used to represent security rating data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Feb 26, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class SecurityRating {

	private String userName;
	private String symbol;
	private BigDecimal rating;
	private String description;
	private Date updateTmstp;
	private BigDecimal userRating;
	private BigDecimal overallRatingPercent;

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

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdateTmstp() {
		return updateTmstp;
	}

	public void setUpdateTmstp(Date updateTmstp) {
		this.updateTmstp = updateTmstp;
	}

	public BigDecimal getUserRating() {
		return userRating;
	}

	public void setUserRating(BigDecimal userRating) {
		this.userRating = userRating;
	}

	public BigDecimal getOverallRatingPercent() {
		return overallRatingPercent;
	}

	public void setOverallRatingPercent(BigDecimal overallRatingPercent) {
		this.overallRatingPercent = overallRatingPercent;
	}

}