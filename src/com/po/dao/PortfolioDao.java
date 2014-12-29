/* File: PortfolioDao.java
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

package com.po.dao;

import java.math.BigDecimal;
import java.util.List;

import com.po.domain.Holding;
import com.po.domain.Portfolio;
import com.po.domain.PortfolioConstraint;
import com.po.domain.HoldingUpdate;

/**
 * Data access interface for portfolio related data.
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
public interface PortfolioDao {

	/**
	 * Method used to retrieve all available portfolios
	 */
	List<Portfolio> retrievePortfolios();

	/**
	 * Method used to retrieve all holdings for the specified account
	 * @param accountNumber The specified account
	 */
	List<Holding> retrieveHoldings(String accountNumber);

	/**
	 * Method used to retrieve the cash holdings for an account
	 * @param accountNumber The specified account
	 * @return The cash holding amount
	 */
	BigDecimal retrieveCashHolding(String accountNumber);

	/**
	 * Method used to update portfolio details
	 * @param portfolio The portfolio to update
	 */
	void updatePortfolio(Portfolio portfolio);

	/**
	 * Method to truncate optimized holdings
	 */
	void truncateOptHoldings();

	/**
	 * Method used to retrieve portfolio constraints
	 */
	List<PortfolioConstraint> retrievePortfolioConstraints();

	/**
	 * Method used to retrieve user portfolio details
	 */
	Portfolio retrievePortfolio(String accountNumber);

	/**
	 * Method used to insert optimized holdings.
	 * @param optHolding List of optimized holdings
	 */
	void insertOptHoldings(List<Holding> optHoldings);

	/**
	 * Method used to insert the optimized cash holding.
	 * @param holdingUpdate The holding to insert
	 */
	void insertOptCashHolding(Holding holding);

	/**
	 * Method used to retrieve portfolio constraints
	 * @param accountNumber The account number
	 */
	Portfolio retrievePortfolioConstraints(String accountNumber);

	/**
	 * Method used to check for acount number availability
	 * @param accountNumber The account number to check for
	 * @return Indicator of account number availability
	 */
	boolean checkAccountNumberAvailability(String accountNumber);

	/**
	 * Method used to retrieve all optimized holdings for the specified account
	 * @param accountNumber The specified account
	 */
	List<Holding> retrieveOptHoldings(String accountNumber);

	/**
	 * Method used to insert a portfolio
	 * @param portfolio The portfolio to insert
	 */
	void insertPortfolio(Portfolio portfolio);

	/**
	 * Method used to insert associated portfolio constraints
	 * @param portfolio The portfolio to insert
	 */
	void insertPortfolioConstraints(Portfolio portfolio);

	/**
	 * Method used to update associated portfolio constraints
	 * @param portfolio The portfolio to update
	 */
	void updatePortfolioConstraints(Portfolio portfolio);

	/**
	 * Method used to update portfolio details
	 * @param portfolio The portfolio to update
	 */
	void updatePortfolioDetails(Portfolio portfolio);

	/**
	 * Method used to delete holdings.
	 * @param accountNumber The account number.
	 */
	void deleteHoldings(String accountNumber);

	/**
	 * Method used to delete optimized holdings.
	 * @param accountNumber The account number.
	 */
	void deleteOptHoldings(String accountNumber);
	
	/**
	 * Method used to insert a holding
	 * @param holdingUpdate The holding to insert
	 */
	void insertHolding(HoldingUpdate holdingUpdate);

	/**
	 * Method used to update an existing holding
	 * @param holdingUpdate The holding to insert
	 */
	void updateHolding(HoldingUpdate holdingUpdate);

	/**
	 * Method used to update the cash holding
	 * @param holdingUpdate The holding to insert
	 */
	void updateCashHolding(HoldingUpdate holdingUpdate);

	/**
	 * Method used to delete a holding
	 * @param holdingUpdate The holding to delete
	 */
	void deleteHolding(HoldingUpdate holdingUpdate);

}