/* File: CompanyClassification.java
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
 * Domain class used to represent company classification data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>Mar 02, 2010   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
public class CompanyClassification {

	private String symbol;
	private BigDecimal output1;
	private BigDecimal output2;
	private Long outlook;
	private Date updateTmstp;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getOutput1() {
		return output1;
	}

	public void setOutput1(BigDecimal output1) {
		this.output1 = output1;
	}

	public BigDecimal getOutput2() {
		return output2;
	}

	public void setOutput2(BigDecimal output2) {
		this.output2 = output2;
	}

	public Long getOutlook() {
		return outlook;
	}

	public void setOutlook(Long outlook) {
		this.outlook = outlook;
	}

	public Date getUpdateTmstp() {
		return updateTmstp;
	}

	public void setUpdateTmstp(Date updateTmstp) {
		this.updateTmstp = updateTmstp;
	}

}