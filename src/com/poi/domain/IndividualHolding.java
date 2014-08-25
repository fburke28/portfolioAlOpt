/* File: IndividualHolding.java
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
 * Domain class used to represent an individual holding.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Apr 10, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class IndividualHolding {

	private Long holdingKey;
	private Long quantity;
	private BigDecimal tradePrice;

	public Long getHoldingKey() {
		return holdingKey;
	}

	public void setHoldingKey(Long holdingKey) {
		this.holdingKey = holdingKey;
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

}