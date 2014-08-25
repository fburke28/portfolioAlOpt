package com.poi.view;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.po.domain.Holding;

/**
 * View class used to return data.
 * 
 * <p>
 * Modification History:<br>
 * <PRE>DATE           AUTHOR              CHANGE<br><PRE>
 * <PRE>-------------- ------------------- ---------------------------<br><PRE>
 * <PRE>May 11, 2011   Finbarr Burke       Initial Version<br><PRE>
 * 
 * @author A412060
 * 
 */
@XmlRootElement
public class HoldingsView {

	private List<Holding> holdings;

	public List<Holding> getHoldings() {
		return holdings;
	}

	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}

}