/* File: UserRatingWeight.java
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

/**
 * Domain class used to represent user rating weights.
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
public class UserRatingWeight {

	private int lowLimit;
	private int hightLimit;
	private BigDecimal gainRatingWeight;
	private BigDecimal lossRatingWeight;

	public int getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(int lowLimit) {
		this.lowLimit = lowLimit;
	}

	public int getHightLimit() {
		return hightLimit;
	}

	public void setHightLimit(int hightLimit) {
		this.hightLimit = hightLimit;
	}

	public BigDecimal getGainRatingWeight() {
		return gainRatingWeight;
	}

	public void setGainRatingWeight(BigDecimal gainRatingWeight) {
		this.gainRatingWeight = gainRatingWeight;
	}

	public BigDecimal getLossRatingWeight() {
		return lossRatingWeight;
	}

	public void setLossRatingWeight(BigDecimal lossRatingWeight) {
		this.lossRatingWeight = lossRatingWeight;
	}

}