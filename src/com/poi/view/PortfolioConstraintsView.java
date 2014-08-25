package com.poi.view;

import javax.xml.bind.annotation.XmlRootElement;

import com.po.domain.Portfolio;

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
public class PortfolioConstraintsView {

	private Portfolio portfolio;

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

}