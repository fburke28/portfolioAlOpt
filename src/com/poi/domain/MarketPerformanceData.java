/* File: MarketPerformanceData.java
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

import java.io.Serializable;

/**
 * Domain class used to represent market data performance for a given time period.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Dec 10, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class MarketPerformanceData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String timeframe;
	private String djiClose;
	private String nasdaqClose;
	private String spClose;

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public String getDjiClose() {
		return djiClose;
	}

	public void setDjiClose(String djiClose) {
		this.djiClose = djiClose;
	}

	public String getNasdaqClose() {
		return nasdaqClose;
	}

	public void setNasdaqClose(String nasdaqClose) {
		this.nasdaqClose = nasdaqClose;
	}

	public String getSpClose() {
		return spClose;
	}

	public void setSpClose(String spClose) {
		this.spClose = spClose;
	}

}